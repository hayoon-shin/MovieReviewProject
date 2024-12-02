
# 🎬 영화 리뷰 프로젝트

## 📖 **프로젝트 개요**  
**영화 리뷰 프로젝트**는 사용자의 시청 기록과 영화 정보를 효율적으로 관리하고 분석할 수 있는 Java 기반의 애플리케이션입니다.  
이 프로젝트는 Oracle 데이터베이스를 활용하여 데이터를 저장하며, CRUD 기능을 통해 영화 감상 기록을 체계적으로 관리할 수 있도록 설계되었습니다.  
사용자들이 자신의 시청 이력을 관리하고 영화에 대한 세부 정보를 확인하며, 영화를 감상한 사용자만 리뷰를 작성할 수 있는 시스템을 제공합니다.

---

## 💡 **주요 기능**  
- **사용자 시청 기록 관리**  
  - 시청 기록 조회, 추가, 수정, 삭제 기능 제공.  
  - 영화별 총 시청 횟수를 자동으로 계산 및 업데이트.  
  - 시청 기록 변경 시 관련 데이터(시청 횟수 등) 자동 반영.

- **영화 정보 통합 관리**  
  - 영화 제목, 개봉일, 장르, 상영 시간 등 세부 정보 제공.  
  - 시청 기록과 영화 정보를 연동하여 데이터 활용도 극대화.

- **데이터베이스 연동**  
  - Oracle SQL을 활용한 안정적이고 효율적인 데이터 관리.  
  - 복잡한 쿼리(Subquery, Aggregate Function 등)로 유용한 데이터 조회 가능.  
  - 트랜잭션 처리로 데이터 일관성과 무결성 보장.

- **확장 가능한 설계**  
  - DAO와 VO 클래스를 활용한 모듈화 및 재사용성 높은 구조.  
  - 사용자 리뷰 및 평점 기능 등 추가 기능을 쉽게 확장 가능.

- **펑션, 트리거, 프로시저 소개**
  - ID 자동 생성 트리거
 ```
CREATE OR REPLACE TRIGGER USER_ID_TRIGGER
BEFORE INSERT ON Users
FOR EACH ROW
BEGIN
    IF :NEW.user_id IS NULL THEN
        :NEW.user_id := 'USER' || TO_CHAR(USER_ID_SEQ.NEXTVAL, 'FM0000');
    END IF;
END;
/
```
 - 패스워드 찾기 펑션
```
CREATE OR REPLACE FUNCTION FIND_PASSWORD_FUNC (
    p_user_id IN VARCHAR2,
    p_email IN VARCHAR2
) RETURN VARCHAR2
AS
    v_password VARCHAR2(255);
BEGIN
    SELECT password
    INTO v_password
    FROM Users
    WHERE user_id = p_user_id
      AND email = p_email;
    RETURN v_password;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN '존재하지 않는 사용자 입니다';
END;
/
```
 - 장르별 영화 찾기 프로시저
```
CREATE OR REPLACE PROCEDURE list_movies_by_genre (
    p_genre IN VARCHAR2,
    p_cursor OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_cursor FOR
        SELECT title, release_date, duration
        FROM Movies
        WHERE genre = p_genre;
END;
/
```

---

## 🚀 **사용 기술**  
- **프로그래밍 언어**: Java 21 (JDK 21.0.4)  
- **데이터베이스**: Oracle SQL  
- **개발 도구**: Eclipse 4.32.0
- **형상 관리**: Git

## 📈 **ERD 다이어그램**
![ERD](https://github.com/user-attachments/assets/3914a11e-ec5a-46f1-9e3e-0483e9fcc6da)

---

## 💻 **실행 화면**
- 회원 가입 화면
![스크린샷 2024-12-02 165648](https://github.com/user-attachments/assets/db661916-6f4f-492d-934f-8ed1181b59f5)
- 영화 조회 화면
![스크린샷 2024-12-02 165742](https://github.com/user-attachments/assets/40813d18-7d25-4dd9-9666-1351d8cc3254)
- 시청 기록 화면
![스크린샷 2024-12-02 165833](https://github.com/user-attachments/assets/396904df-568a-43d8-b16b-2c3265df36f5)
- 시청자 리뷰 화면
![스크린샷 2024-12-02 170054](https://github.com/user-attachments/assets/a125d7eb-f486-4d9c-be08-f6e7aab4eec8)

