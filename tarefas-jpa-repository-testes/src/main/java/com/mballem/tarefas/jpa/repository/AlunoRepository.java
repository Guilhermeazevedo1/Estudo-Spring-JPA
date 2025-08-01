package com.mballem.tarefas.jpa.repository;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.dto.AlunoArmarioDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("Select a from Aluno a where a.nome Like :nome% ")
    List<Aluno> findByPrimeiroNome(@Param("nome") String nome, @Param("order") Sort sort);

    @Query("select a.nome as nome, a.matricula as matricula, a.anoLetivo as anoLetivo, a.armario.numero as armario " +
            "from Aluno a where a.matricula in :matricula " +
            "order by a.anoLetivo desc, a.nome asc")
    List<AlunoArmarioDto> findByMatriculas(@Param("matricula") List<String> matricula);

    @Query("select a from Aluno a where a.armario IS NULL")
    List<Aluno> findAndRemoveAlunosSemArmarios();
}
