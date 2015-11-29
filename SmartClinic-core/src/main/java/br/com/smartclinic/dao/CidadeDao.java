package br.com.smartclinic.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.smartclinic.model.Cidade;

public class CidadeDao extends ParentDao{
	
	private static final long serialVersionUID = 1L;
	
	private static CidadeDao instance;
	
	public static CidadeDao getInstance(){
		if(instance == null){
			instance = new CidadeDao();
		}
		return instance;
	}
	
	public Cidade inserir(Cidade cidade, boolean confirmaTransacao){
		cidade = super.incluir(cidade);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
		
		return cidade;
	}
	
	public Cidade alterar(Cidade cidade, boolean confirmaTransacao){
		cidade = super.alterar(cidade);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
		
		return cidade;
	}
	
	public void excluir(Cidade cidade, boolean confirmaTransacao){
		super.excluir(cidade);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
	}
	
	public Cidade consultarPorId(Long id){
		Cidade result = super.consultarPorId(Cidade.class, id);
		return result;
	}
	
	public List<Cidade> listar(Cidade cidade, boolean isExato){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		hql.append(" select bean from ").append(Cidade.class.getName()).append(" bean ");
		hql.append(" where 1=1 ");
		
		if(cidade.getNome() != null && !cidade.getNome().equals("")){
			if(isExato){
				hql.append(" and LOWER(bean.nome) = LOWER(:nome) ");
				parametros.put("nome", cidade.getNome());
			}else{
				hql.append(" and LOWER(bean.nome) like LOWER(:nome) ");
				parametros.put("nome", "%" + cidade.getNome() + "%");
			}
		}
		
		if(cidade.getEstado() != null && !cidade.getEstado().equals("")){
			if(isExato){
				hql.append(" and LOWER(bean.estado) = LOWER(:estado) ");
				parametros.put("estado", cidade.getEstado());
			}else{
				hql.append(" and LOWER(bean.estado) like LOWER(:estado) ");
				parametros.put("estado", "%" + cidade.getEstado() + "%");
			}
		}
		
		return super.listar(Cidade.class, hql, parametros);
	}
}
