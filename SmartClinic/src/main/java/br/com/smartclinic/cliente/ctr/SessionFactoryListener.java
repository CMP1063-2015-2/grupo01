package br.com.smartclinic.cliente.ctr;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.smartclinic.dataGenerator.SmartClinicDataGenerator;
import br.com.smartclinic.model.TransferEntity;
import br.com.smartclinic.utils.HibernateUtil;

public class SessionFactoryListener implements ServletContextListener{
	
	private static SessionFactory sessionFactory;
	
	public static final Set<Class<? extends TransferEntity>> smartClinicAnnotatedClasses = new HashSet<Class<? extends TransferEntity>>();
	
	static{
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Usuario.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Pessoa.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Medico.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Telefone.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Agenda.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Cidade.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Compromisso.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Consulta.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Convenio.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Endereco.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.FichaMedica.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Paciente.class);
		smartClinicAnnotatedClasses.add(br.com.smartclinic.model.Secretario.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		sessionFactory.close();
		sessionFactory = null;
		HibernateUtil.setSessionFactory(null);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		sessionFactory = getSessionFactory();
		HibernateUtil.setSessionFactory(sessionFactory);
		SmartClinicDataGenerator.incluirDadosSmartClinic(arg0.getServletContext().getRealPath("/"));
	}
	
	@SuppressWarnings("deprecation")
	private static SessionFactory getSessionFactory(){
		try{
			if(sessionFactory == null){
				Configuration conf = new Configuration();
				
				for(Class<? extends TransferEntity> clazz : smartClinicAnnotatedClasses){
					conf.addAnnotatedClass(clazz);
				}
				sessionFactory = conf.configure().buildSessionFactory();
			}
			return sessionFactory;
		}catch(Throwable e){
			e.printStackTrace();
			return null;
		}
	}

}
