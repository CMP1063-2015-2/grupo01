package br.com.smartclinic.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.smartclinic.model.Pessoa;

public class PessoaDao extends ParentDao {
	
	private static final long serialVersionUID = 1L;
	
	private static PessoaDao instance;
	
	public static PessoaDao getInstance(){
		if(instance == null){
			instance = new PessoaDao();
		}
		return instance;
	}
	
	public List<Pessoa> listar(Pessoa pessoa){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		hql.append(" select bean from ").append(Pessoa.class.getName()).append(" bean ");
		hql.append(" where 1=1 ");
		
		if(pessoa.getCpf() != null && !pessoa.getCpf().equals("")){
			hql.append(" and LOWER(bean.cpf) = LOWER(:cpf) ");
			parametros.put("cpf", pessoa.getCpf());
		}
		
		if(pessoa.getRg() != null && !pessoa.getRg().equals("")){
			hql.append(" and bean.rg = :rg ");
			parametros.put("rg", pessoa.getRg());
		}
		
		return super.listar(Pessoa.class, hql, parametros);
	}
}
