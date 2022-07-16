package com.example.demowebproject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Реализация паттерна репозиторий
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    /**
     * Поиск чуваков по имени
     *
     * @param name имя по которому мы ищем
     * @return список сотрудников
     */
    List<Employee> findByName(String name);
}