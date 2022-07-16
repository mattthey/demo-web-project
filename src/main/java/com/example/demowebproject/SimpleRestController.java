package com.example.demowebproject;

import java.util.List;

/**
 *
 */
public interface SimpleRestController
{
    /**
     * @return
     */
    String hello();

    List<Employee> getAll();

    Employee addEmployee(Employee newEmployee);

    void deleteEmployee(Long id);

    void editEmployee(Long id, Employee employee);

    /**
     * Изменение чувака, если он существует, иначе создание нового
     *
     * @param id идентификатор изменяемого сотрудника
     * @param employee чувак которого добавляем/изменяем
     * @return изменены
     */
    Employee editEmployeeOrCreate(Long id, Employee employee);

    Employee getEmployeeById(Long id);

    /**
     * Получить чуваков по имени
     *
     * @param name имя по которому нужно найти чуваков
     * @return список чуваков с совпадающим именем
     */
    List<Employee> getEmployeesByName(String name);
}