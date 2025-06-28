# 카카오 테크 캠퍼스 과제#2

## API 명세서

| 기능 | Method | URL | Request | Response | 상태코드 |
| --- | --- | --- | --- | --- | --- |
| 작성자 등록 | **`POST`** | /v1/api/author | 요청 body | X | 201: 등록 완료 |
| Todo 등록 | **`POST`** | /v1/api/todo | 요청 body | X | 201: 등록 완료 500: 없는 사용자 |
| Todo 조회 | **`GET`** | /v1/api/todo |  | Todo 정보 리스트 | 200: 조회 완료 |
| Todo 조회 ( 페이지네이션 ) | **`GET`** | /v1/api/todo/pagination?page={value}&size={value}&authorId={value}&updatedAt={value} | 요청 param | Todo 정보 리스트 | 200: 조회 완료 |
| Todo 삭제 | **`DELETE`** | /v1/api/todo/{id} | 요청 param 요청 body | X | 204: 삭제 완료 |
| Todo 수정 | **`PATCH`** | /v1/api/todo/{id} | 요청 param 요청 body | X | 200: 수정 완료 |
| Todo 단일조회 | **`GET`** | /v1/api/todo/{id} | 요청 param | Todo 정보 | 200: 조회 완료 500: 없는 ID |

## ERD

<img width="621" alt="스크린샷 2025-05-25 오후 10 41 44" src="https://github.com/user-attachments/assets/cf3f9b34-5a71-4ee0-b18f-2cf8ff1569cd" />
