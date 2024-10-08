package EmployeeManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeId(Long id){
		return employeeRepository.findById(id);
	}
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(Long id, Employee employeeDetails) {
		Employee employee=employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
		
		employee.setName(employeeDetails.getName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setDob(employeeDetails.getDob());
		employee.setDepartment(employeeDetails.getDepartment());
		
		return employeeRepository.save(employee);
	}
	
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

}
