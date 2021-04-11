# delivery-share-Webpage

[융소] 종합설계 캡스톤 프로젝트 Delivery-Share

## 매장 사장님이 접근 가능한 웹 페이지 서비스 구현

- 주문접수 Tab : 실시간으로 유저의 주문을 받아보고 이를 처리해야 함
- 리뷰관리 Tab : 유저가 남긴 리뷰를 확인하고 답글을 달 수 있어야 함
- 메뉴관리 Tab : 매장에서 관리하는 메뉴에 대해 기본적인 수정 및 등록
- 매장정보 Tab : 매장의 기본적인 정보를 제공

# demo page

https://kangyeollee.github.io/delivery-share-project

# Skill Stack

- React CRA / Hooks / rendering optimazation(React.memo, loadable)
- Typescript (tsconfig-paths)
- Styled Components (every styled design without library)
- react-lazy-load-image-component
- Craco alias (for Webpack Setting customizing)

# Updates

### 04.09

- 주문접수 / 리뷰관리 TAB Pagination 적용

### 04.11

- 더미데이터 포맷 변경: 주문사항 메뉴와 요청 모두 fetch

# Issues

### 04.10

- type checking 관련 event type을 MouseEvent로 마킹할 시 오류 발생 [MenuModal.index.tsx / OrderModal.index.tsx]

### 04.11

- 더미데이터 fetch 관련 Type을 임시로 선언 : 추후 백엔드와 데이터베이스 연동 시 실제 데이터 타입으로 변경 필요
