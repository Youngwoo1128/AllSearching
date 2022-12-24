package com.woojoo.allsearching.data.paging

import android.util.Log
import androidx.paging.*
import com.woojoo.allsearching.data.mapping.searchingResultMapping
import com.woojoo.allsearching.data.network.NetworkAPI
import com.woojoo.allsearching.domain.entites.Documents

/*
* PagingSource 를 만들기 위해서는 Paging Key인 데이터 로드를 위한 식별자와 데이터를 정의해야한다.
* 페이지 번호 또는 offset과 같은 식별자를 Retrofit 또는 Room에 전달하여 데이터를 받아오는 것이다.
* */
class SearchingPagingDataSource(
    private val query: String,
    private val networkAPI: NetworkAPI
) : PagingSource<Int, Documents>() {

    /*
    * 스와이스 Refresh나 데이터 업데이트 등으로 현재 목록을 대체할 새 데이터를 로드할 때 사용된다.
    *    PagingData는 Component에서 설명한 것 처럼 새로고침 될 때마다 상응하는 PagingData를 생성해야한다.
    *    즉, 수정이 불가능 하고 새로운 인스턴스를 만들어야 한다.
    * 가장 최근에 접근한 인덱스인 anchorPosition으로 주변 데이터를 다시 로드한다.
    * */

    override fun getRefreshKey(state: PagingState<Int, Documents>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    /*
    * Load(params: LoadParams<Key)
    * load 함수는 사용자가 스크롤 할 때마다 데이터를 비동기 적으로 가져온다.
    * 파라미터인 LoadParams 객체는 로드 작업과 관련된 정보를 가지고 있다.
    *   params.key에 현재 페이지 index를 관리한다. 처음 데이터를 로드할 때에는 null이 반환된다.
    *   params.loadSize는 가져올 데이터의 갯수를 관리한다.
    * load 함수는 LoadResult를 반환한다.
    *   LoadResult.Page : 로드에 성공한 경우, 데이터와 이전 다음 페이지 Key가 포함된다.
    *   LoadResult.Error : 오류가 발생한 경우
    * */

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Documents> {
        val page = params.key ?: 1
        Log.d("currentPageValue", "$page")
        return try {
            val response = searchingResultMapping(
                networkAPI.searchImageResult(query, page, 30).documents,
                networkAPI.searchVideoResult(query, page, 30).documents
            ).toList()

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

}
