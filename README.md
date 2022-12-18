# All Searching

**목표** : Kakao Open API 이용한 검색 어플리케이션 개발 


**개발언어** : Kotlin </br>
**IDE** : Android Studio </br>
**네트워킹** : Retrofit 2.0, OkHttp </br>
**아키텍처** : MVVM, Clean Architecture </br>
**DI** : Hilt </br>
**Database** : Room </br>
**그 외** : ViewBinding, DataBinding, Coroutine, Android Jetpack, WebView </br>

</br>


</br>
Retorfit을 통한 Kakao Open API를 사용하여 네트워킹 작업 및 Android Room을 활용한 즐겨찾기 기능</br>
--------------------------
</br>
<img src="https://user-images.githubusercontent.com/77264918/208038036-13b66668-7ddd-456e-99d3-6a1b830d4ce8.gif" width="300" height="700" />
요약 
1. image 타입의 Response 와 video 타입의 response를 하나의 list로 관리 </br>
2. image model과 video model의 viewType을 지정해 RecyclerView에서 두개의 ViewType 사용 </br>
3. Android Room의 Insert를 활용하여 즐겨찾기 기능 구현 </br>
4. 네트워크 에러가 있을 시 Exception 처리</br>


</br>
</br>


Android Room을 활용한 로컬 데이터베이스 
--------------------------
</br>
<img src="https://user-images.githubusercontent.com/77264918/208038687-777e3772-cc66-408c-b87e-06a7959ebbc7.gif" width="300" height="700" />
</br>


Clean Architecture 기반 개발
--------------------------
<img src="https://user-images.githubusercontent.com/77264918/206121269-d2eff147-1af3-4cc2-bda4-5169f6f076f3.jpeg" width="600" height="600" />


Presentor -> domain <- data

AndroidX 페이징
--------------------------
<img src="https://user-images.githubusercontent.com/77264918/208039735-84ddb0af-8ec2-4dd1-ab58-4e817b983adb.png" width="1000" height="300" />
</br>



Hilt
--------------------------
<img src="https://user-images.githubusercontent.com/77264918/206464948-04f9fd8b-84bc-468b-9644-df6c53e3c5de.png" width="600" height="600" />

