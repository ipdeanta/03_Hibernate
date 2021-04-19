package com.serbatic.course;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.serbatic.course.datamodel.dao.ClientDAO;
import com.serbatic.course.datamodel.dao.ProviderDAO;
import com.serbatic.course.datamodel.entities.annotations.Client;
import com.serbatic.course.datamodel.entities.annotations.Statement;
import com.serbatic.course.datamodel.entities.annotations.StatementPk;
import com.serbatic.course.datamodel.entities.xmlmapping.Provider;
import com.serbatic.course.datamodel.utils.HibernateUtil;

public class HibernateMain {
	
	private static Logger logger = LogManager.getLogger(HibernateMain.class);
	
	static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		String methodName = HibernateMain.class.getSimpleName() + ".main()";
		logger.info(String.format("%1$s: >>>>>> Main execution started.", methodName));
		// La SessionFactory se instancia 1 vez por ejecución del programa.
		// Todas las sesiones de hibernate se obtienen de esa SessionFactory;	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int modifiedProviderId = 1;
		try {
		  // Las opereaciones save/update/delete
			tx = session.beginTransaction();
			
			// Insertamos proveedores
			int numProviders = 5;
			ProviderDAO.insertProviders(session, numProviders);
			
			// Recuperamos y listamos proveedores
			List<Provider> providers = ProviderDAO.getAllProviders(session);
			logger.info(String.format("%1$s: number of providers = %2$s.", methodName, providers.size()));
			providers.stream().forEach(x -> logger.info(String.format("%1$s: ---> %2$s.", methodName, x.toString())));
			
			// Actualizamos proveedor 1
			Provider provider = providers.stream()
  										 .filter(x -> x.getProviderId() == modifiedProviderId)
  										 .findFirst()
  										 .orElse(null);
			if (provider != null) {
				provider.setBusinessName("Nueva razón social");
			}
			
			// Insertamos clientes
			int numClients = 2;
			ClientDAO.insertClients(session, numClients);
			
			// Recuperamos y listamos clientes
			List<Client> clients = ClientDAO.getAllClients(session);
			logger.info(String.format("%1$s: number of clients = %2$s.", methodName, clients.size()));
			clients.stream().forEach(x -> logger.info(String.format("%1$s: ---> %2$s.", methodName, x.toString())));
			
			// Insertamos factura
			int clientId = numClients; // por ejemplo, el último insertado
			Client client = clients.stream().filter(x -> x.getClientId() == clientId).findFirst().orElse(null);
			StatementPk statementPk = new StatementPk(client.getClientId());
			Statement statement = new Statement();
			statement.setStatementPk(statementPk);
			statement.setIdFromClient(client);
			statement.setAmount(100);
			LocalDateTime billingDate = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
			statement.setBillingDate(billingDate.format(formatter));
			session.save(statement);
			tx.commit();
			
			// Recuperamos y listamos clientes con facturas
			List<Client> clientsWithStatements = ClientDAO.getClientsWithStatements(session);
      logger.info(String.format("%1$s: number of clients with statements = %2$s.", methodName, clientsWithStatements.size()));
      clientsWithStatements.stream().forEach(x -> logger.info(String.format("%1$s: ---> %2$s.", methodName, x.toString())));
		} catch (Exception e) {
		  if (tx != null) {
		    tx.rollback();
		  }
			logger.error(String.format("%1$s: error when inserting registries.", methodName), e);
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		
		// Abrimos nueva sesión y recuperamos el proveedor 1 para comprobar que se ha actualizado
		session = HibernateUtil.getSessionFactory().openSession();
		Provider modifiedProvider = ProviderDAO.getProvider(session, modifiedProviderId);
		logger.info(String.format("%1$s: ---> %2$s.", methodName, modifiedProvider.toString()));
		session.close();
		
		logger.info(String.format("%1$s: >>>>>> Main execution finished.", methodName));
	}	
}
