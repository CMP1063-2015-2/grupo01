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
				logger.debug("##### Abrindo sessão " + session.hashCode());
			}catch(Throwable e){
				logger.error("##### Falha ao abir a sessão");
				e.printStackTrace();
			}
		}
		return session;
	}
	
	public static JdbcTransaction beginTransaction(){
		if(!isTransacaoAberta()){
			try{
				transaction = (JdbcTransaction) getSession().beginTransaction();
				logger.debug("###### Abrindo transação " + transaction.hashCode());
			}catch(Throwable e){
				logger.debug("###### Falha ao abrir a transação");
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
				logger.debug("###### Commitando transação " + transaction.hashCode());				
			}catch(Throwable e){
				logger.debug("###### Falha ao commitar transação " + transaction.hashCode());
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void rollbackTransaction(){
		if(isTransacaoAberta()){
			try{
				transaction.rollback();
				logger.debug("###### Realizando rollback na transação " + transaction.hashCode());
			}catch(Throwable e){
				logger.debug("###### Falha ao dar roolback na transação " + transaction.hashCode());
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void endTransaction(){
		if(transaction != null){
			logger.debug("###### Finalizando transação " + transaction.hashCode());
			transaction = null;
		}
		
		if(session != null){
			logger.debug("##### Finalizando sessão " + session.hashCode());
			session = null;
		}
	}
	
}
