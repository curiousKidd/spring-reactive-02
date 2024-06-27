package com.itvillage.section03.class01;

import com.itvillage.utils.Logger;
import com.itvillage.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * flatMap 기본 개념 예제
 *  - 비동기적으로 동작할 경우, emit 되는 순서를 보장하지 않는다.
 */
public class FlatMapExample03 {
    /**
      * 해당 사항을 이해하기 어려웠는데, 플랫맵과 맵을 같은 로직상에서 비교하니 이해하기가 쉬워짐
     * 중첩 for문을 한개의 for문 처럼 (평탄화) 해주는 느낌에서의 flat dlek
      */

    public static void main(String[] args) {
        Flux
            .range(2, 8)
            .flatMap(dan -> Flux
                                .range(1, 9)
                                .publishOn(Schedulers.parallel())
                                .map(n -> dan + " * " + n + " = " + dan * n))

            .subscribe(Logger::onNext);

        TimeUtils.sleep(200L);

        Flux
                .range(2, 8)
                .map(dan -> Flux
                        .range(1, 9)
                        .publishOn(Schedulers.parallel())
                        .map(n -> dan + " * " + n + " = " + dan * n))

                .subscribe(Logger::onNext);

        TimeUtils.sleep(200L);
    }
}
