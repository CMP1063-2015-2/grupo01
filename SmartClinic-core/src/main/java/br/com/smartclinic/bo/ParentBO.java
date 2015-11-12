package br.com.smartclinic.bo;

import br.com.smartclinic.dao.MedicoDao;
import br.com.smartclinic.dao.UsuarioDao;

public class ParentBO {
	
	protected UsuarioDao usuarioDao;
	protected MedicoDao medicoDao;
	
	protected ParentBO(){
		this.usuarioDao = UsuarioDao.getInstance();
		this.medicoDao = MedicoDao.getInstance();
	}
}
