package br.com.smartclinic.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.smartclinic.model.Medico;
import br.com.smartclinic.model.Pessoa;

public class MedicoDao extends ParentDao{
	
	private static MedicoDao instance;
	
	public static MedicoDao getInstance(){
		if(instance == null){
			instance = new MedicoDao();
		}
		return instance;
	}
	
	public Medico inserir(Medico medico, boolean confirmaTransacao){
		medico = super.incluir(medico);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
		
		return medico;
	}
	
	public Medico alterar(Medico medico, boolean confirmaTransacao){
		medico = super.alterar(medico);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
		
		return medico;
	}
	
	public void excluir(Medico medico, boolean confirmaTransacao){
		super.excluir(medico);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
	}
	
	public Medico consultarPorId(Long id){
		Medico result = super.consultarPorId(Medico.class, id);
		return result;
	}
	
	public List<Medico> listar(Medico medico, boolean isExato){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		hql.append(" select bean from ").append(Medico.class.getName()).append(" bean ");
		hql.append(" inner join bean.pessoa pessoa ");
		hql.append(" where 1 = 1 ");
		
		if(medico.getPessoa() != null){
			Pessoa pessoa = medico.getPessoa();
			
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
					hql.append(" and pessoa.cpf = :medicoCpf ");
					parametros.put("medicoCpf", pessoa.getCpf());
				}else{
					hql.append(" and pessoa.cpf like :medicoCpf ");
					parametros.put("medicoCpf", "%" + pessoa.getCpf() + "%");
				}
			}
			
			if(pessoa.getRg() != null && !pessoa.getRg().equals("")){
				if(isExato){
					hql.append(" and pessoa.rg = :medicoRg ");
					parametros.put("medicoRg", pessoa.getRg());
				}else{
					hql.append(" and pessoa.rg like :medicoRg ");
					parametros.put("medicoRg", "%" + pessoa.getRg() + "%");
				}
			}
		}
		
		if(medico.getCrm() != null && !medico.getCrm().equals("")){
			if(isExato){
				hql.append(" and bean.crm = :medicoCrm ");
				parametros.put("medicoCrm", medico.getCrm());
			}else{
				hql.append(" and bean.crm like :medicoCrm ");
				parametros.put("medicoCrm", "%" + medico.getCrm() + "%");
			}
		}
		
		return super.listar(Medico.class, hql, parametros);
	}

}
