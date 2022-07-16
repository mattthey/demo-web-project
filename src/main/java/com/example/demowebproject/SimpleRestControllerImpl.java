package com.example.demowebproject;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Пример рест коннектора
 */
@RestController
public class SimpleRestControllerImpl implements SimpleRestController
{
    private static final Random RANDOM = new Random();

    private final EmployeeRepository employeeRepository;

    public SimpleRestControllerImpl(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    /**
     * @return получить приветственное сообщение
     */
    @Override
    @GetMapping("/hello")
    public String hello()
    {
        return "hello " + RANDOM.nextInt();
    }

    @Override
    @GetMapping("/allEmployee")
    public List<Employee> getAll()
    {
        return employeeRepository.findAll();
    }

    @Override
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee newEmployee)
    {
        return employeeRepository.save(newEmployee);
    }

    @Override
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable Long id)
    {
        employeeRepository.deleteById(id);
    }

    @Override
    @PutMapping("/editEmployee/{id}")
    public void editEmployee(@PathVariable Long id, @RequestBody Employee employee)
    {
        final Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty())
            return;
        employeeRepository.save(employee);
    }

    /**
     * Изменение чувака, если он существует, иначе создание нового
     *
     * @param id идентификатор изменяемого сотрудника
     * @param employee чувак которого добавляем/изменяем
     * @return изменены
     */
    @Override
    @PutMapping("/editEmployeeOrCreate/{id}")
    public Employee editEmployeeOrCreate(@PathVariable @Nullable Long id, @RequestBody Employee employee)
    {
        // todo исправить, чтобы id мог не передаваться
        final Optional<Employee> employeeOpt = id != null
                ? employeeRepository.findById(id)
                : Optional.empty();
        employee.setId(employeeOpt.isPresent() ? id : null);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id)
    {
        // todo
        return null;
    }

    @Override
    @GetMapping("/getEmployeesByName")
    public List<Employee> getEmployeesByName(@RequestParam("name") String name)
    {
        return employeeRepository.findByName(name);
    }
}