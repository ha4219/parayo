package io.github.ha4219.ttd

import java.time.LocalDateTime

class DivDetail {
    constructor(title : String = "",
                startDateTime : LocalDateTime = LocalDateTime.now(),
                endDateTime : LocalDateTime = LocalDateTime.now()
    ) {
        var endDateTime = endDateTime
        var startDateTime = startDateTime
        var title = title
        var createdTime = LocalDateTime.now()
        var revisedTime = LocalDateTime.now()
    }


}