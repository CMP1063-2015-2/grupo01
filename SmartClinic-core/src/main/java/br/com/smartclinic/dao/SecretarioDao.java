package br.com.smartclinic.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.smartclinic.model.Secretario;
import br.com.smartclinic.model.Pessoa;

public class SecretarioDao extends ParentDao{
	
	private static final long serialVersionUID = 1L;
	
	private static SecretarioDao instance;
	
	public static SecretarioDao getInstance(){
		if(instance == null){
			instance = new SecretarioDao();
		}
		return instance;
	}
	
	public Secretario inserir(Secretario secretario, boolean confirmaTransacao){
		secretario = super.incluir(secretario);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
		
		return secretario;
	}
	
	public Secretario alterar(Secretario secretario, boolean confirmaTransacao){
		secretario = super.alterar(secretario);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
		
		return secretario;
	}
	
	public void excluir(Secretario secretario, boolean confirmaTransacao){
		super.excluir(secretario);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
	}
	
	public Secretario consultarPorId(Long id){
		Secretario result = super.consultarPorId(Secretario.class, id);
		return result;
	}
	
	public List<Secretario> listar(Secretario secretario, boolean isExato){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		hql.append(" select bean from ").append(Secretario.class.getName()).append(" bean ");
		hql.append(" inner join bean.pessoa pessoa ");
		hql.append(" where 1 = 1 ");
		
		if(secretario.getPessoa() != null){
			Pessoa pessoa = secretario.getPessoa();
			
			if(pessoa.getNome() != null && !pessoa.getNome().equals("")){
				if(isExato){
					hql.append(" and LOWER(pessoa.nome) = LOWER(:pessoaNome) ");
					parametros.put("pessoaNome", pessoa.getNome());
				}else{
					hql.append(" and LOWER(pessoa.nome) like LOWER(:pessoaoNome) ");
					parametros.put("pessoaNome", "%" + pessoa.getNome() + "%");
				}
			}
			
			if(pessoa.getCpf() != null && !pessoa.getCpf().equals("")){
				if(isExato){
					hql.append(" and pessoa.cpf = :secretarioCpf ");
					parametros.put("secretarioCpf", pessoa.getCpf());
				}else{
					hql.append(" and pessoa.cpf like :secretarioCpf ");
					parametros.put("secretarioCpf", "%" + pessoa.getCpf() + "%");
				}
			}
			
			if(pessoa.getRg() != null && !pessoa.getRg().equals("")){
				if(isExato){
					hql.append(" and pessoa.rg = :secretarioRg ");
					parametros.put("secretarioRg", pessoa.getRg());
				}else{
					hql.append(" and pessoa.rg like :secretarioRg ");
					parametros.put("secretarioRg", "%" + pessoa.getRg() + "%");
				}
			}
		}
		
		return super.listar(Secretario.class, hql, parametros);
	}

}
