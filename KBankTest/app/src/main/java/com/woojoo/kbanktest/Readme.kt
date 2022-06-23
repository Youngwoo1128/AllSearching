package com.woojoo.kbanktest

// 1. 이미지를 검색해서 보관함에 수집하는 안드로이드 앱 작성

// 2. 검색 키워드 하나에 이미지 검색과 동영상 검색을 동시에 사용, 두 검색 결과를 합친 리스트를 사용
// 이미지 검색 API - https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image 의 thumbnail_url 필드
// 동영상 검색 API - https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video 의 thumbnail_url 필드
// 두 검색 결과를 datatime 필드를 이용해 정렬하여 출력. (최신부터 나타나도록)

//3. 검색 결과는 5분간 캐시하여, 5분 이내 동일 키워드로 검색했을 때 네트워크 통신 없이 결과를 보여줌

//4. UI는 Fragment 2개를 사용 (버튼이나 탭 선택 시 전환)
// 4-1. 첫번째 fragment: 검색 결과
// - 검색어를 입력할 수 있음
// - 검색된 이미지 리스트가 나타남. 각 아이템에는 이미지와 함께 날짜와 시간을 표시
// - 스크롤을 통해 다음 페이지를 불러옴
// - 리스트에서 특정 이미지를 선택하여 "내 보관함"으로 저장할 수 있음
// - 보관된 이미지는 특별한 표시를 보여줌 (좋아요/별표/하트)

// 4_2. 두번쨰 fragment: 내 보관함
// 검색 결과에서 보관했던 이미지들이 보관한 순서대로 보임
// 보관한 이미지 리스트는 앱 재시작 후 다시 보여야함 (SharedPreference, 로컬 DB등)

// 5. 적혀있지 않은 내용은 자유롭게 작성하면 됨(요건을 침해하지 않는 범위에서 기능 추가등)

// 개발요건
// 검색데이터는 https://developers.kakao.com/product/search 의 OpenAPI 를 사용
// 오픈 소스 사용가능. 참고로 카뱅은 retrofit, kotlinx-coroutines-android, rxJava 등을 사용

// -> 정리
// 통신 -> Retrofit
// Response -> DataBinding
// UI -> ViewBinding
// DI -> Hilt
// Design Pattern -> MVVM (AAC)
// 로컬 DB -> Room
// Image -> Glide

// SharedPreference -> ???
// Paging -> Paginate?? Android Paging3 일단 나중에 다시 생각하기???

// 욕심 좀 더 내서 Response의 item 클릭시 상세 화면으로 넘어가서 댓글, 좋아요, 기능 추가 (댓글은 Local DB)
// 좋아요 누르면 Push 받기 (현업에서 Push 한번도 못해봄)
// 이번 기회에 패키지 정하는 법도 고민해보기

// git은.. 기능별?? 그냥 master에? 고민해보기...

// Clean Architecture -> 대철님 께서 보내주신 블로그 보고 구조 살펴보기 (예제소스)
// Clean Architecture 설명 : https://velog.io/@sery270/Android%EC%9D%98-Clean-Architecture%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90-n9ihbaj4
// Clean Architecture 좋은 블로그 : https://leveloper.tistory.com/205