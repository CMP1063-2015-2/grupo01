package br.com.smartclinic.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.smartclinic.model.TransferEntity;
import br.com.smartclinic.utils.HibernateUtil;

public class ParentDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected <T extends TransferEntity> T incluir(T bean){
		try{
			Session session = HibernateUtil.getSession();
			
			HibernateUtil.beginTransaction();
			session.save(bean);
			session.flush();
		}catch(Throwable e){
			HibernateUtil.rollbackTransaction();
		}
		return bean;
	}
	
	protected <T extends TransferEntity> T alterar(T bean){
		try{
			Session session = HibernateUtil.getSession();
			
			HibernateUtil.beginTransaction();
			session.update(bean);
			session.flush();
		}catch(Throwable e){
			HibernateUtil.rollbackTransaction();
		}
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends TransferEntity> T excluir(T bean){
		try{
			Session session = HibernateUtil.getSession();
			
			HibernateUtil.beginTransaction();
			bean = (T) session.load(retornaNomeClasse(bean), (Serializable) bean.getId());
			session.delete(bean);
			session.flush();
		}catch(Throwable e){
			HibernateUtil.rollbackTransaction();
		}
		return bean;
	}
	
	protected void confirmaTransacao(){
		try{
			HibernateUtil.commitTransaction();
		}catch(Throwable e){
			HibernateUtil.rollbackTransaction();
		}finally{
			HibernateUtil.endTransaction();
		}
	}
	
	private static String retornaNomeClasse(Object objeto) {
		if (objeto.getClass().getName().indexOf("$") > 0) {
			return objeto.getClass().getSuperclass().getName();
		}

		return objeto.getClass().getName();
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends TransferEntity> T consultarPorId(Class<T> beanClass, Serializable id){
		T result = null;
		try{
			Session session = HibernateUtil.getSession();
			
			HibernateUtil.beginTransaction();
			result = (T) session.get(beanClass, id);
			session.flush();
		}catch(Throwable e){
			HibernateUtil.endTransaction();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T extends TransferEntity> List<T> listar(Class<T> clazz, StringBuilder hql, Map<String, Object> parametros) {
		HibernateUtil.beginTransaction();
		Query query = HibernateUtil.getSession().createQuery(hql.toString());
		
		if(parametros != null){
			for(String key: parametros.keySet()){
				query.setParameter(key, parametros.get(key));
			}
		}
		
		return query.list();
	}
	
	public Object uniqueResult(StringBuilder hql, Map<String, Object> parametros){
		HibernateUtil.beginTransaction();
		
		Query query = HibernateUtil.getSession().createQuery(hql.toString());
		
		if(parametros != null){
			for(String key: parametros.keySet()){
				query.setParameter(key, parametros.get(key));
			}
		}
		
		query.setMaxResults(1);
		return query.uniqueResult();
	}

}
