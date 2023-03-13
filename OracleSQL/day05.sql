-- EX1
SELECT DEPARTMENT_ID FROM EMPLOYEES WHERE FIRST_NAME = 'Neena';
SELECT DEPARTMENT_NAME FROM DEPARTMENTS WHERE DEPARTMENT_ID='90';

SELECT DEPARTMENT_NAME FROM DEPARTMENTS
WHERE DEPARTMENT_ID = (SELECT DEPARTMENT_ID FROM EMPLOYEES WHERE FIRST_NAME='Neena');
-- 서브쿼리를 사용하는 이유: 서브쿼리 결과값을 WHERE에 활용하기 위해서


-- EX2
SELECT LAST_NAME, DEPARTMENT_ID, SALARY
FROM EMPLOYEES
WHERE DEPARTMENT_ID = (SELECT DEPARTMENT_ID FROM EMPLOYEES WHERE FIRST_NAME='Neena')
      AND SALARY > (SELECT SALARY FROM EMPLOYEES WHERE FIRST_NAME='Neena');


-- EX3
SELECT LAST_NAME, DEPARTMENT_NAME, SALARY
FROM EMPLOYEES
LEFT JOIN DEPARTMENTS USING(DEPARTMENT_ID)
WHERE DEPARTMENT_ID = (SELECT DEPARTMENT_ID FROM EMPLOYEES WHERE LAST_NAME='Austin')
      AND
      SALARY = (SELECT SALARY FROM EMPLOYEES WHERE LAST_NAME='Austin');


-- EX4
SELECT SALARY FROM EMPLOYEES WHERE JOB_ID='ST_MAN'; -- 5800/8200
SELECT SALARY FROM EMPLOYEES WHERE JOB_ID='IT_PROG'; -- 4200/9000

SELECT LAST_NAME, JOB_ID, SALARY
FROM EMPLOYEES
WHERE JOB_ID = 'IT_PROG'
      AND
      SALARY > ANY(SELECT SALARY FROM EMPLOYEES WHERE JOB_ID='ST_MAN');


-- EX5
SELECT SALARY FROM EMPLOYEES WHERE JOB_ID='IT_PROG';

SELECT LAST_NAME, JOB_ID, SALARY
FROM EMPLOYEES
WHERE SALARY IN (SELECT SALARY FROM EMPLOYEES WHERE JOB_ID='IT_PROG');

SELECT LAST_NAME, JOB_ID, SALARY
FROM EMPLOYEES
WHERE SALARY=4200
   OR SALARY=4800
   OR SALARY=6000
   OR SALARY=9000;


-- EX6
-- 구문 길이에 따른 빠른 속도 보장 X
SELECT EMPLOYEE_ID "사원 번호", LAST_NAME "이름",
       CASE
            WHEN EMPLOYEE_ID IN (SELECT MANAGER_ID FROM EMPLOYEES) THEN '관리자'
            ELSE '직원'
       END "구분" -- SELECT 구문 
FROM EMPLOYEES
ORDER BY 3,1;

SELECT EMPLOYEE_ID "사원 번호", LAST_NAME "이름", '관리자' "구분"
-- '': RECORD, "": COL_NAME_ALIAS
FROM EMPLOYEES
WHERE EMPLOYEE_ID IN (SELECT MANAGER_ID FROM EMPLOYEES)
UNION
SELECT EMPLOYEE_ID "사원 번호", LAST_NAME "이름", '직원' "구분"
FROM EMPLOYEES
WHERE EMPLOYEE_ID NOT IN (SELECT MANAGER_ID FROM EMPLOYEES WHERE MANAGER_ID IS NOT NULL)
ORDER BY 3, 1;

SELECT EMPLOYEE_ID "사원번호", LAST_NAME "이름", '관리자' "구분"
FROM EMPLOYEES E
WHERE EXISTS (SELECT NULL FROM EMPLOYEES WHERE E.EMPLOYEE_ID=MANAGER_ID)
-- EXISTS: T/F 반환, COL 입력 필요 X
UNION
SELECT EMPLOYEE_ID "사원번호", LAST_NAME "이름", '직원' "구분"
FROM EMPLOYEES E
WHERE NOT EXISTS (SELECT NULL FROM EMPLOYEES WHERE E.EMPLOYEE_ID=MANAGER_ID)
ORDER BY 3, 1;


-- EX7
-- 동일부서에 대한 직무별 평균급여
SELECT DEPARTMENT_NAME, JOB_TITLE, ROUND(AVG(SALARY), 2) "AVG_SAL"
FROM EMPLOYEES
JOIN DEPARTMENTS USING(DEPARTMENT_ID)
JOIN JOBS USING(JOB_ID)
GROUP BY ROLLUP(DEPARTMENT_NAME, JOB_TITLE)
ORDER BY 1;
-- 출력 형식
-- 1학년 1반, 1학년 2반, 2학년 1반, 2학년 2반


-- EX8
SELECT DEPARTMENT_NAME, JOB_TITLE, ROUND(AVG(SALARY), 2) "AVG_SAL"
FROM EMPLOYEES
JOIN DEPARTMENTS USING(DEPARTMENT_ID)
JOIN JOBS USING(JOB_ID)
GROUP BY CUBE(DEPARTMENT_NAME, JOB_TITLE)
ORDER BY 2;
-- 출력 형식
-- 1학년 1반, 1학년 2반, 2학년 1반, 2학년 2반
-- 1학년, 2학년
-- 1반, 2반, 1반, 2반


-- EX9
SELECT JOB_TITLE, ROUND(AVG(SALARY), 2) AS "AVG_SAL"
FROM EMPLOYEES
JOIN DEPARTMENTS USING(DEPARTMENT_ID)
JOIN JOBS USING(JOB_ID)
GROUP BY GROUPING SETS((JOB_TITLE), ());
-- (): ALL ROWS를 추가하여 JOB_TITLE=NULL인 RECORD도 출력

SELECT ROUND(AVG(SALARY), 2) FROM EMPLOYEES;
-- SELECT AVG FROM EMP GROUP BY GROUPING SETS(()); 그룹화 후 해당 그룹들의 평균
-- SELECT AVG FROM EMP; EMP 사원들의 평균

SELECT DISTINCT JOB_ID FROM EMPLOYEES;
SELECT * FROM JOBS;


-- 문제1
SELECT FIRST_NAME, SALARY
FROM EMPLOYEES
WHERE SALARY = (SELECT MIN(SALARY) FROM EMPLOYEES);


-- 문제2
SELECT DISTINCT DEPARTMENT_ID FROM DEPARTMENTS;
SELECT DISTINCT DEPARTMENT_ID FROM EMPLOYEES ORDER BY 1;

SELECT DEPARTMENT_ID, SUM(SALARY)
FROM EMPLOYEES
GROUP BY DEPARTMENT_ID;
-- GROUP BY를 통해 DEPARTMENT_ID SELECT 가능

SELECT MAX(SUM(SALARY))
FROM EMPLOYEES
GROUP BY DEPARTMENT_ID;
-- MAX(SUM(SALARY)): 1개값 반환, DEPARTMENT_ID: 그룹화된 12개값 반환
-- 행의 개수가 맞지 않아 오류
-- 만일 1개의 DEPARTMENT_ID를 RETURN 받고 싶으면 HAVING절(GROUP에 대한 WHERE절) 사용

SELECT MAX(SALARY)
FROM(SELECT SUM(SALARY) SALARY
     FROM EMPLOYEES
     GROUP BY DEPARTMENT_ID);

SELECT DEPARTMENT_NAME, SUM(SALARY)
FROM EMPLOYEES
JOIN DEPARTMENTS USING(DEPARTMENT_ID)
GROUP BY DEPARTMENT_NAME
HAVING SUM(SALARY) = (SELECT MAX(SUM(SALARY))
                      FROM EMPLOYEES
                      GROUP BY DEPARTMENT_ID);
-- 그룹에 대한 조건은 WHERE이 아닌 HAVING


-- 문제3 (1302/$)
SELECT FIRST_NAME "사원명",
       JOB_ID "업무 ID",
       TO_CHAR(SALARY*1302, 'L999,999,999') "급여"
FROM EMPLOYEES
WHERE (JOB_ID='FI_ACCOUNT' OR JOB_ID='SA_REP')
-- JOB_ID IN('FI_ACCOUNT', 'SA_REP')
      AND
      SALARY >= ALL(SELECT SALARY FROM EMPLOYEES WHERE JOB_ID='IT_PROG')
ORDER BY 3 DESC;


-- 문제4
SELECT LAST_NAME "사원명",
       JOB_ID "업무 ID",
       JOB_TITLE "직무",
       TO_CHAR(TRUNC(SALARY, -3), '$99,999') "급여"
FROM EMPLOYEES
JOIN JOBS USING(JOB_ID)
GROUP BY LAST_NAME, JOB_ID, JOB_TITLE, SALARY
HAVING (TRUNC(SALARY, -3)=AVG(SALARY))
ORDER BY 4;

-- 1단계: 업무별 AVG(SALARY) 구하기
SELECT JOB_ID "업무 ID",
       TRUNC(AVG(SALARY), -3) "평균 급여"
FROM EMPLOYEES
JOIN JOBS USING(JOB_ID)
GROUP BY JOB_ID; -- GROUP BY COL 대상만 SELECT 가능

-- TABLE JOIN USING(JOB_ID)
SELECT LAST_NAME "사원명",
       JOB_ID "업무 ID",
       JOB_TITLE "직무",
       SALARY "급여"
FROM EMPLOYEES
JOIN JOBS USING(JOB_ID);

-- WHERE 조건절 추가
-- WHERE JOB_ID='AD_PRES' AND SALARY=24000;
-- WHERE JOB_ID='AD_VP' AND SALARY=17000;
-- ... JOB_ID 반복
-- WHERE (JOB_ID, SALARY) IN ();
-- (SELECT JOB_ID, TRUNC(AVG(SALARY), -3) FROM EMPLOYEES GROUP BY JOB_ID);
-- 상기 SQL 구문이 JOB_ID로 구분한 JOB_ID와 TRUNC_SALARY를 RETURN
-- 두개의 COL을 대상으로 비교 가능

SELECT LAST_NAME "사원명",
       JOB_ID "업무 ID",
       JOB_TITLE "직무",
       TO_CHAR(TRUNC(SALARY, -3), '$99,999') "급여"
FROM EMPLOYEES
JOIN JOBS USING(JOB_ID)
WHERE (JOB_ID, SALARY) IN (SELECT JOB_ID, TRUNC(AVG(SALARY), -3) FROM EMPLOYEES GROUP BY JOB_ID)
ORDER BY 4;