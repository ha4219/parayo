package io.github.ha4219.ttd

import java.time.LocalDate
import java.time.LocalDateTime


class Timer{
    var curTime = LocalDateTime.now()
    var curDate = LocalDate.now()

    fun getDate() : LocalDate{
        setCurrentTime()
        return curDate
    }

    fun getTime() : LocalDateTime{
        setCurrentTime()
        return curTime
    }

    fun setCurrentTime() {
        curTime = LocalDateTime.now()
        curDate = LocalDate.now()
    }
}