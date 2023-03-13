-- 연산 순서 유의(From  - Where - Group by - having - select - order by)

-- ex1
select employee_id, last_name, salary from employees;


-- ex2
select employee_id "사원번호", last_name "이름", salary "급여"
from employees;

select employee_id "사원번호", last_name "이름", 12*salary "연봉"
from employees;


-- ex3
select employee_id "사원번호",
       first_name || '' || last_name "이름",
       12*salary || '달러' "연봉"
       FROM employees where employee_id <102;


-- ex4
select first_name || ' ' || last_name "이름"
from employees;


-- ex5
select distinct department_id from employees;


-- ex6
select last_name, hire_date, department_id
from employees
where department_id in (10, 90);


-- ex7
select last_name, hire_date, salary
from employees
where salary>=2500 and salary<3500;


-- ex8
select * from employees where last_name='King';

select * from employees where lower(last_name)='king';


-- ex9
select last_name, job_id, department_id
from employees
where job_id like '%MAN%';


-- ex10
select last_name, job_id, department_id
from employees
where job_id like 'IT%';


-- ex11
select last_name, salary, commission_pct
from employees
where commission_pct is not null;

select last_name, salary, commission_pct
from employees
where commission_pct is null;


-- ex12
select employee_id, last_name, job_id
from employees
where job_id='FI_MGR' or job_id='FI_ACCOUNT';

select employee_id, last_name, job_id
from employees
where job_id in('FI_MGR', 'FI_ACCOUNT');


-- ex13
select employee_id, last_name, salary
from employees
where salary>=10000 and salary<=20000;

select employee_id, last_name, salary
from employees
where salary between 10000 and 20000;


-- 문제1
select last_name || ' ' || 'is a ' || job_id as "Employee Detail"
from employees
where rownum<=3 order by last_name;


-- 문제 2
select last_name || ' ' || 'is a ' || job_id as "Employee Detail" from employees where rownum<=3 order by last_name;


-- 문제 3
select first_name ||' '|| last_name "사원명", '$' || salary "월급", department_id "부서코드"
from employees
where (salary<=2500 or salary>=3000) and department_id=90;
-- where salary not between 2500 and 3000;


-- 문제4
select last_name "이름", job_id "업무ID", salary || '원' "급여"
from employees
where (job_id in ('SA_REP', 'AD_PRES')) and salary>10000;
-- cf. 전체 컬럼에 대한 중복 제거를 실행 할 경우, sub query를 사용해야 함


-- 문제5
select distinct job_id from employees;
select job_id from employees group by job_id;


-- 문제6
select employee_id, last_name, hire_date
from employees
-- where hire_date>='2005-01-01' and hire_date<='2005-12-31';
-- where hire_date between '2005-01-01' and '2005-12-31';
-- where hire_date like '05%' -- 2005에서 20을 제외하고 조건문을 작성해도 됨
where to_char(hire_date, 'yyyy') = '2005'; -- casting
