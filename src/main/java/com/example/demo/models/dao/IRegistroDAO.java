package com.example.demo.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Registro;
@Repository
public interface IRegistroDAO extends JpaRepository<Registro,Long> {
	
	@Query(nativeQuery=true, value="select * from registro re "
			+ "where DATEDIFF( re.fecha_hora_inicio , now()) = 0 "
			+ "order by re.fecha_hora_inicio asc ")
	public List<Registro> findbyhoy();
	
	
	@Query(nativeQuery=true, value=""
			+ "select * "
			+ "from registro re "
			+ "where DATEDIFF( re.fecha_hora_inicio ,  ?1  ) = 0 "
			+ "order by re.fecha_hora_inicio asc ")
	public List<Registro> findbyFecha(String fecha);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true, value=""
			+ "update registro re "
			+ "set re.anotaciones = ?2 , "
			+ "re.fecha_hora_finalizacion = ?3 , "
			+ "re.fecha_hora_inicio = ?4 , "
			+ "re.homework = ?5 , "
			+ "re.id_clase = ?6 "
			+ "where re.id_registro = ?1")
	public void updateById(long id, String anotaciones, String fecha_fin, String fecha_ini, String homework, Long clase);
}
