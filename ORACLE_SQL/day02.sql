-- 연산 순서 유의(From  - Where - Group by - having - select - order by)

-- ex1
select last_name, department_id, hire_date
from employees
order by 2 desc; -- oracle 1부터 시작, Null: Infinite 취급


-- ex2
select last_name, department_id, hire_date
from employees
order by 2 desc, 3 asc;


-- ex1
select employee_id, last_name, department_id
from employees
where lower(last_name)='higgins';


-- ex2
select mod(10, 3) from dual;


-- ex3
select round(35765.357, 2) from dual;
select round(35765.357, 0) from dual;
select round(35765.357, -3) from dual;


-- ex4
select trunc(35765.357, 2) from dual;
select floor(35765.357) from dual;

select trunc(35765.357, 0) from dual;

select trunc(35765.357, -3) from dual;


-- ex5
select concat('Hello ', 'World') from dual;


-- ex6
create table text(
    str1 char(20),
    str2 varchar2(20));
    
insert into text(str1, str2) values('angel', 'angel');
insert into text(str1, str2) values('사천사', '사천사');

commit;

select lengthb(str1), lengthb(str2) from text;
select length(str1), length(str2) from text;


-- ex7
select length('korea') from dual;
select length('코리아') from dual;

select lengthb('korea') from dual;
select lengthb('코리아') from dual;


-- ex8
select instr('HelloWorld', 'W') from dual;
select instr('HelloWorld', 'o', -5) from dual;
select instr('HelloWorld', 'o', -1) from dual;


-- ex9
select substr('I am very happy', 6, 4) from dual;
select substr('I am very happy', 6) from dual;


-- ex10
select width_bucket(74, 0, 100, 10) from dual;


-- ex11
select rtrim('test  ') || 'exam' from dual;


-- ex12
select sysdate from dual;
select to_char(sysdate, 'YYYY"년" MM"월" DD"일"') "오늘 날짜" from dual;
select to_char(sysdate, 'HH"시 " MI"분 " SS"초"') "현재 시각" from dual;
select to_char(sysdate, 'HH24"시 " MI"분 " SS"초"') "현재 시각(24시간제)" from dual;


-- ex13
select add_months(sysdate, 7) from dual;


-- ex14
select last_day(sysdate) from dual;
select last_day('2004-02-01') from dual;
select last_day('2005-02-01') from dual;


-- ex15
select months_between('95-10-21', '94-10-20') from dual; -- return double -> round: int
select round(months_between('95-10-21', '94-10-20'), 0) from dual;

select last_name, to_char(salary, 'L99,999.00')
from employees
where last_name='King';


-- ex16
select to_char(to_date('97/9/30', 'YY-MM-DD'), 'YYYY-MON-DD') from dual;
select to_char(to_date('97/9/30', 'RR-MM-DD'), 'RRRR-MON-DD') from dual;

select to_char(to_date('17/9/30', 'YY-MM-DD'), 'YYYY-MON-DD') from dual;
select to_char(to_date('17/9/30', 'RR-MM-DD'), 'RRRR-MON-DD') from dual;


-- ex17
select last_name, hire_date from employees where hire_date='05/09/30';
select last_name, hire_date from employees where hire_date='05/9/30';

select to_char(sysdate, 'YYYY-MM-DD') from dual;
select to_char(sysdate, 'YYYY-fmMM-DD') from dual;



-- ex18
select max(salary),
       min(salary),
       trunc(avg(salary), 0),
       to_char(sum(salary), 'L99,999,999')
from employees;


-- ex19
select department_id from employees;
select count(department_id) from employees;
select count(*) from employees;
select count(distinct department_id) from employees;
select count(distinct nvl(department_id, 0)) from employees;
select distinct nvl(department_id, 0) from employees;


-- ex20
select job_id, decode(job_id,
                      'SA_MAN', 'Sales Dept',
                      'SA_REP', 'Sales Dept',
                      'Another') "분류"
from employees
order by 2;

select job_id, case job_id
                    when 'SA_MAN' then 'Sales Dept'
                    when 'SA_REP' then 'Sales Dept'
                    else 'Another'
               end "분류"
from employees
order by 2;

select job_id, case
               when job_id='SA_MAN' then 'Sales Dept'
               when job_id='SA_REP' then 'Sales Dept'
               else 'Another'
       end "분류"
from employees
order by 2;


-- EX21
SELECT RANK(3000) WITHIN GROUP(ORDER BY SALARY DESC)"RANK"
-- RANK(): PARAMETER=COL_VALUE
FROM EMPLOYEES;

SELECT EMPLOYEE_ID, SALARY, RANK() OVER(ORDER BY SALARY DESC) "RANK"
FROM EMPLOYEES;


-- EX22
SELECT EMPLOYEE_ID, SALARY, DEPARTMENT_ID, FIRST_VALUE(SALARY)
OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) "HIGHSAL_DEPTID"
FROM EMPLOYEES;

SELECT EMPLOYEE_ID, LAST_NAME, SALARY, DEPARTMENT_ID, ROW_NUMBER()
OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) RNUM
FROM EMPLOYEES;


-- 문제1
select last_name "이름", salary*12 "연봉"
from employees
order by salary*12 desc;


-- 문제2
select employee_id,
concat(first_name, ' '|| last_name) "Name",
-- concat: 2개 인자, 추가시 || 활용
length(concat(first_name, last_name)) "Length"
from employees
where substr(last_name, -1) = 'n';


-- 문제3
select last_day(sysdate) - sysdate "Remain_date" from dual;
-- 날짜형 사칙연산 가능: date -> second 변형


-- 문제4
SELECT LAST_NAME, TO_CHAR(HIRE_DATE, 'DD-MON-YYYY') "HIRE_NATE"
-- MON: 세자리 약어, 영어: JAN, 한국어: 1월
FROM EMPLOYEES
WHERE HIRE_DATE < '2005-01-01';
-- 날짜는 컴퓨터 내부에서 초단위로 표시하기 때문에 비교 연산자 사용 가능

-- 문제5
select count(nvl(commission_pct, 0)) - count(commission_pct) from employees;
select count(*) from employees where commission_pct is null;
-- 전체 col을 대상으로 null 값 확인(*)
select count(nvl(commission_pct, 0)) from employees where commission_pct is null;

select last_name, commission_pct from employees where commission_pct is null;


-- 문제6
select employee_id "사원번호", last_name "사원명",
case when salary<10000 then '초급'
     when salary<20000  then '중급'
     else '고급'
     end "구분"
-- '데이터'와 "Alias" 구분
-- case 와 when 사이에 컬럼을 사용 시, 대소비교(<, >, =) 불가능
from employees
order by 3 asc, 2 asc;


-- 문제7
select employee_id "사원번호",
       last_name "이름",
       to_char(salary, '$99,999') "급여",
       to_char(nvl(commission_pct,0)*salary, '$9,999') "커미션",
       to_char(salary*12*(1+nvl(commission_pct, 0)), '$999,999') "연봉"
from employees
order by 5 desc, 3 desc;
-- order by 별칭 가능, col_num 가능


-- 문제8
select employee_id "사원번호", last_name "이름", nvl(manager_id, '1000') "매니저ID"
from employees;
