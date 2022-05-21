package com.example.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.entity.Horario;

public interface IHorarioDAO extends JpaRepository<Horario,Long> {

}
