package com.mballem.tarefas.jpa.dao;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.dto.AlunoArmarioDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AlunoDao {

    @PersistenceContext
    private EntityManager manager;

    /**
     * TESTE 1 - Complete o metodo com a operação JPA solicitada
     */
    @Transactional(readOnly = true)
    public Aluno findByNomeCompleto(String nome) {
        String query = "Select a from Aluno a where a.nome like :nome";
        return this.manager.createQuery(query, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    /**
     * TESTE 2 - Complete o metodo com a operação JPA solicitada
     */
    @Transactional(readOnly = true)
    public Aluno findByMatricula(String matricula) {
        String query = "Select a from Aluno a where a.matricula like :matricula";
        return this.manager.createQuery(query, Aluno.class)
                .setParameter("matricula", matricula)
                .getSingleResult();
    }

    /**
     * TESTE 3 - Complete o metodo com a operação JPA solicitada
     */
    @Transactional(readOnly = true)
    public Long findByAnoLetivo(int inicio, int fim) {
        String query = "Select count(1) from Aluno a where a.anoLetivo between :inicio and :fim";
        return this.manager.createQuery(query, Long.class)
                .setParameter("inicio", inicio)
                .setParameter("fim", fim)
                .getSingleResult();
    }

    /**
     * TESTE 4 - Complete o metodo com a operação JPA solicitada
     */
    @Transactional(readOnly = true)
    public List<Aluno> findByNomeParcial(String nome) {
        String query = "Select a from Aluno a where a.nome like :nome";
        return this.manager.createQuery(query, Aluno.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    /**
     * TESTE 5 - Complete o metodo com a operação JPA solicitada
     */
    @Transactional(readOnly = true)
    public Aluno findByNumeroArmario(Integer numero) {
        String query = "Select a from Aluno a where a.armario.numero = :numero";
        return this.manager.createQuery(query, Aluno.class)
                .setParameter("numero", numero)
                .getSingleResult();
    }

    /**
     * TESTE 6 - Complete o metodo com a operação JPA solicitada
     */
    @Transactional(readOnly = true)
    public List<Long> findByNumerosDeArmarios(List<Integer> numeros) {
        String query = "Select a.id from Aluno a where a.armario.numero in :numeros";
        return this.manager.createQuery(query, Long.class)
                .setParameter("numeros", numeros)
                .getResultList();
    }

    /**
     * TESTE 7 - Complete o metodo com a operação JPA solicitada
     */
    @Transactional(readOnly = false)
    public Aluno updateNomeById(String matricula, String nome) {
        Aluno aluno = findByMatricula(matricula);
        aluno.setNome(nome);
        return aluno;
    }

    /**
     * TESTE 8 - Complete o metodo com a operação JPA solicitada
     */
    @Transactional(readOnly = true)
    public List<AlunoArmarioDto> findAlunosAndArmariosByNomeAndAnoLetivo(String nome, int anoLetivo) {
        String query = "Select new com.mballem.tarefas.jpa.dto.AlunoArmarioDto(a.aluno.nome, a.numero) " +
                "from Armario a where a.aluno.nome like :nome and a.aluno.anoLetivo = :anoLetivo " +
                "order by a.aluno.nome asc";
        return this.manager.createQuery(query, AlunoArmarioDto.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("anoLetivo", anoLetivo)
                .getResultList();
    }

}
