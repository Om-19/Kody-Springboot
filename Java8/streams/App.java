package com.streams.pract;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
	public static void main(String[] args) {
		
		List<Department> dls = List.of(
			    new Department(1, "IT", "Ahmedabad"),
			    new Department(2, "HR", "Mumbai"),
			    new Department(3, "Finance", "Delhi"),
			    new Department(4, "Sales", "Bangalore"),
			    new Department(5, "Marketing", "Pune")
			);
		
		List<Employee> els = List.of(
			    new Employee(1, "Meera", 20000, LocalDate.of(2022, 1, 10), true, EmployeeType.FULL_TIME, dls.get(0)),
			    new Employee(2, "Neha", 60000, LocalDate.of(2021, 5, 15), true, EmployeeType.FULL_TIME, dls.get(1)),
			    new Employee(3, "Rahul", 45000, LocalDate.of(2024, 5, 20), true, EmployeeType.CONTRACT, dls.get(2)),
			    new Employee(4, "Priya", 70000, LocalDate.of(2020, 7, 25), true, EmployeeType.FULL_TIME, dls.get(3)),
			    new Employee(5, "Karan", 65000, LocalDate.of(2022, 9, 5), false, EmployeeType.PART_TIME, dls.get(4)),
			    new Employee(6, "Sneha", 48000, LocalDate.of(2023, 2, 11), true, EmployeeType.CONTRACT, dls.get(0)),
			    new Employee(7, "Vikram", 80000, LocalDate.of(2019, 11, 30), true, EmployeeType.FULL_TIME, dls.get(1)),
			    new Employee(8, "Anjali", 52000, LocalDate.of(2024, 8, 18), true, EmployeeType.PART_TIME, dls.get(2)),
			    new Employee(9, "Rohit", 61000, LocalDate.of(2020, 4, 22), false, EmployeeType.FULL_TIME, dls.get(3)),
			    new Employee(10, "Meera", 25000, LocalDate.of(2018, 12, 1), true, EmployeeType.FULL_TIME, dls.get(4)),
			    new Employee(11, "Priya", 28000, LocalDate.of(2022, 9, 22), false, EmployeeType.FULL_TIME, dls.get(4)),
			    new Employee(12, "Meera", 35000, LocalDate.of(2014, 2, 1), true, EmployeeType.PART_TIME, dls.get(2))
			);		
		
		// salary > 60000
		System.out.println("\n01");
		List<Employee> l1 = els.stream().filter(x -> x.getSalary() > 60000).collect(Collectors.toList());
		for(Employee e : l1) {
			System.out.println(e.getName() + " " + e.getSalary());
		}
		System.out.println();
		
		els.stream().filter(x -> x.getSalary() > 60000).map(Employee::getName).forEach(System.out::println);
		System.out.println();
		
		// employee which are active 
		System.out.println("\n02");
		els.stream().filter(x -> x.isActive() == false).map(Employee::getName).forEach(System.out::println);
		System.out.println();
		
		// count employee
		System.out.println("\n03");
		Long i = els.stream().count();
		System.out.println(i);
		System.out.println();
		System.out.println();
		
		// employee belonging to it department
		System.out.println("\n04");
		els.stream().filter(x -> x.getDepartment().getDeptName().equals("IT")).map(Employee::getName).forEach(System.out::println);
		System.out.println();
		
		// list of salaries of emp
		System.out.println("\n05");
		els.stream().map(Employee::getSalary).forEach(System.out::println);
		System.out.println();
		
		// sum of salary of employee
		System.out.println("\n06");
		Double sum = els.stream().mapToDouble(Employee::getSalary).sum();
		OptionalDouble avg = els.stream().mapToDouble(Employee::getSalary).average();
		OptionalDouble mx = els.stream().mapToDouble(Employee::getSalary).max();

		System.out.println(sum +" " + avg + " " + mx);
		System.out.println();
		
		//sort salary
		System.out.println("\n07");
		els.stream().sorted((a,b) -> Double.compare(a.getSalary(), b.getSalary())).map(Employee::getSalary).forEach(System.out::println);
		System.out.println();
		
		// name of emp with highest salary
		System.out.println("\n08");
		String name = els.stream()
					.max(Comparator.comparingDouble(Employee::getSalary))
					.map(Employee::getName)
					.orElse(null);
		System.out.println(name);
		System.out.println();
		
		// group by dept
		System.out.println("\n09");
		Map<Department, List<Employee>> l2 = els.stream().collect(Collectors.groupingBy(e -> e.getDepartment()));
		for(Map.Entry<Department, List<Employee>> e : l2.entrySet()) {
			System.out.println(e.getKey().getDeptName() + " " + e.getValue());
		}
		System.out.println();
	
		l2.forEach((dept, employees) -> {
		    System.out.println(dept + " -> ");
		    employees.forEach(e -> System.out.println("   " + e.getName()));
		});
	
		System.out.println("\n13");
		// 13) Highest paid employee in each department 
		Map<Department, Employee> m13 =els.stream()
				.collect(Collectors.groupingBy(
						x -> x.getDepartment(), 
						Collectors.collectingAndThen(
							Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
							Optional::get
						)
				));
		m13.forEach((dept, emp) ->
			System.out.println(dept.getDeptName() + " -> " + emp.getName() + " : " + emp.getSalary())
		);
		
		System.out.println("\n14");
		// collect by employe type
		Map<EmployeeType, Long> m14 = els.stream()
				.collect(Collectors.groupingBy(
						Employee::getEmployeeType,
						Collectors.counting()
					));
		m14.forEach((type, count) ->
			System.out.println(type + " : " + count)
		);
				
		System.out.println("\n15");
		// Second Highest salary
		Employee e15 = els.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
				.skip(1)
				.findFirst()
				.orElse(null);
		System.out.println(e15.toString());
		
		System.out.println("\n16");
		// partittion by active inactive
		Map<Boolean, List<Employee>> l15 = els.stream().collect(Collectors.partitioningBy(Employee::isActive));
		l15.forEach((type, emp) -> {
			System.out.println(type + " : " + emp);
		});
		
		System.out.println("\n17");
		//Average salary per department
		Map<Department, Double> m17 = els.stream().collect(
				Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.averagingDouble(Employee::getSalary)
						)
				);
		m17.forEach((dept, val) -> {
			System.out.println(dept.getDeptName() + " : " + val);
		});
		
		System.out.println("\n18");
		// top 3 employee
		List<Employee> l18 = els.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).limit(3).collect(Collectors.toList());
		l18.forEach(e -> 
			System.out.print(e.getName() + " ")
		);
		System.out.println();
		
		System.out.println("\n19");
		// Department with highest avg salary
		Department d19 = els.stream()
				.collect(Collectors.groupingBy(
				Employee::getDepartment,
				Collectors.averagingDouble(Employee::getSalary)	
			))
			.entrySet()
			.stream()
			.max(Map.Entry.comparingByValue())
			.map(Map.Entry::getKey)
			.orElse(null);
		System.out.println(d19.getDeptName());
		
		System.out.println("\n20");
		// group by department & employee type
		Map<Department, Map<EmployeeType, List<Employee>>> l20 = els.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.groupingBy(
								Employee::getEmployeeType
								)
						));
		l20.forEach((dept, map) -> {
			System.out.println(dept.getDeptName()+" ");
			
			map.forEach((type, list) -> {
				System.out.println(type);
				list.forEach(emp -> System.out.println("    " + emp.getName()));
			});
		});
		
		System.out.println("\n21");
		// employee joined in last 2 years + sort by salary desc
		List<Employee> l21 = els.stream().filter(x -> x.getJoiningDate().isAfter(LocalDate.now().minusYears(2))).sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
		l21.forEach(x -> System.out.println(x.getName() + ": " + x.getSalary()));
		
		System.out.println("\n22");
		// Department wise highest salary
		Map<Department, Double> m22 = els.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.collectingAndThen(
								Collectors.mapping(
										Employee::getSalary, 
										Collectors.maxBy(Comparator.naturalOrder())
						),
						Optional::get
					)
		));
		m22.forEach((dept, val) -> {
			System.out.println(dept.getDeptName() + " : " + val);
		});		
		
		/****************************************************************************************************/
		
		// Find Duplicate employee names  
		System.out.println("\n01");
		Set<String> s1 = els.stream().collect(Collectors.groupingBy(
					Employee::getName, 
					Collectors.counting()))
				.entrySet()
				.stream()
				.filter(x -> x.getValue() > 1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet()); 
		s1.forEach(x -> System.out.println(x));
		
		//Find first non repeating employee name
		System.out.println("\n02");
		Optional<String> s2 = els.stream().collect(Collectors.groupingBy(
				Employee::getName,
				LinkedHashMap::new,         // maintains insertion order
				Collectors.counting()       // Hashmap doesnt preserve insertion order
				))
				.entrySet()
				.stream()
				.filter(x -> x.getValue() == 1)
				.map(Map.Entry::getKey)
				.findFirst();
		System.out.println(s2);
		
		// salary > department average
		System.out.println("\n03");
		Map<Department, Double> m3 = els.stream().collect(Collectors.groupingBy(
				Employee::getDepartment,
				Collectors.averagingDouble(Employee::getSalary)
				));
		
		List<Employee> l3 = els.stream()
				.filter(e -> e.getSalary() > m3.get(e.getDepartment()))
				.collect(Collectors.toList());
		l3.forEach(x -> System.out.println(x.getName()));
		
		// Single pipeline
		System.out.println("\n03 Single Pipeline: ");
		List<Employee> l33 = els.stream()
			    .collect(Collectors.collectingAndThen(
			        Collectors.groupingBy(
			            Employee::getDepartment,
			            Collectors.toList()
			        ),
			        map -> map.values().stream()
			            .flatMap(list -> {
			                double Davg = list.stream()
			                    .mapToDouble(Employee::getSalary)
			                    .average()
			                    .orElse(0.0);

			                return list.stream()
			                    .filter(e -> e.getSalary() > Davg);
			            })
			            .collect(Collectors.toList())
			    ));
		l33.forEach(x -> System.out.println(x.getName()));
		
	}
}

