package br.com.smartclinic.utils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.transaction.internal.jdbc.JdbcTransaction;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;
	private static JdbcTransaction transaction;
	private static Logger logger = Logger.getLogger(HibernateUtil.class.getName());
	
	public static void setSessionFactory(SessionFactory factory){
		sessionFactory = factory;
	}
	
	public static Session getSession(){
		if(session == null){
			try{
				session = sessionFactory.getCurrentSession();
				logger.debug("##### Abrindo sess�o " + session.hashCode());
			}catch(Throwable e){
				logger.error("##### Falha ao abir a sess�o");
				e.printStackTrace();
			}
		}
		return session;
	}
	
	public static JdbcTransaction beginTransaction(){
		if(!isTransacaoAberta()){
			try{
				transaction = (JdbcTransaction) getSession().beginTransaction();
				logger.debug("###### Abrindo transa��o " + transaction.hashCode());
			}catch(Throwable e){
				logger.debug("###### Falha ao abrir a transa��o");
				e.printStackTrace();
			}
		}
		return transaction;
	}
	
	public static boolean isTransacaoAberta(){
		return transaction != null;
	}
	
	public static void commitTransaction(){
		if(isTransacaoAberta()){
			try{
				transaction.commit();
				logger.debug("###### Commitando transa��o " + transaction.hashCode());				
			}catch(Throwable e){
				logger.debug("###### Falha ao commitar transa��o " + transaction.hashCode());
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void rollbackTransaction(){
		if(isTransacaoAberta()){
			try{
				transaction.rollback();
				logger.debug("###### Realizando rollback na transa��o " + transaction.hashCode());
			}catch(Throwable e){
				logger.debug("###### Falha ao dar roolback na transa��o " + transaction.hashCode());
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void endTransaction(){
		if(transaction != null){
			logger.debug("###### Finalizando transa��o " + transaction.hashCode());
			transaction = null;
		}
		
		if(session != null){
			logger.debug("##### Finalizando sess�o " + session.hashCode());
			session = null;
		}
	}
	
}
