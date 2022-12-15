package com.woojoo.allsearching.data.mapping

import com.woojoo.allsearching.data.network.response.NetworkError
import com.woojoo.allsearching.domain.entites.Error


fun NetworkError.errorMapping() = Error(
    status = status,
    message = message,
    throwable = throwable
)