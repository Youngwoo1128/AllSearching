package com.woojoo.allsearching.data.datasources

import android.util.Log
import com.woojoo.allsearching.domain.entites.DeleteResult
import com.woojoo.allsearching.domain.entites.Researching

class DeleteDataSourceImpl: DeleteDataSource {

    /*
    * Entity를 삭제하고 그 삭제한걸 adapter에게 알리기는 방법을 수도 없이 고민해봤다.
    * 시도해본 test는 아래와 같다.
    * 1. 별도의 index 값을 만들어 삭제할때마다 id값 재조정
    * 2. soft delete 사용
    *
    * 1 번 같은 케이스는 정말 아니다 싶어 제외
    * 2 번은 굳이 soft delete를 사용할 필요가 없다고 판단. 이 DB를 참조하는 별도의 DB도 없고, 이 DB는 독립적인 DB이기 때문에
    * 적합하지 않다고 판단.
    * 그래서 hard delete를 구현하고 이진 탐색을 통해 해당 index를 빠르게 찾고 어댑터에게 알리기
    *
    * insert 하게 되면 id는 autoIncrement 로 인해 자동으로 정렬되어있음,
    * 이 특징을 이용해 이진 탐색으로 구현하기
    * */
    override fun getDeletedItemIndex(researchingList: List<Researching>, findItem: Researching): DeleteResult {
        Log.d("entrance", "DeleteDataSourceImpl")
        return findDeleteIdIndex(researchingList, 0, researchingList.size, findItem)
    }

    private fun findDeleteIdIndex(list: List<Researching>, start: Int, end: Int, item: Researching): DeleteResult {
        try {
            val pivot = (start + end) / 2

            if (list[pivot].id == item.id) {
                return DeleteResult.DeleteSuccess(pivot)
            }

            return if (list[pivot].id!! > item.id!!) {
                findDeleteIdIndex(list, start, pivot, item)
            } else {
                findDeleteIdIndex(list, pivot, end, item)
            }
        } catch (e: StackOverflowError) {
            return DeleteResult.DeleteFail
        } catch (e: Exception) {
            Log.d("exception reason", "${e.message}")
            return DeleteResult.DeleteFail
        }
    }
}