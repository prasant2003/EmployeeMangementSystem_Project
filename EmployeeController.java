package EmployeeManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
     
	@Autowired
	private EmployeeService employeeService;
	
	//create new Employee
	@PostMapping
	public Employee createEmployee(Employee employee) {
		return employeeService.createEmployee(employee);
		
	}
	//get all employee
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployee();
	}
	
	//Get employee by Id
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmpoyeeById(Long id){
		Employee employee=employeeService.getEmployeeId(id).orElseThrow(()->new RuntimeException("Employee not found"));
		
		return ResponseEntity.ok(employee);
	}
	// Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
	
}
