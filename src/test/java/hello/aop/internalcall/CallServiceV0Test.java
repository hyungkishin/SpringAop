package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

    @Autowired CallServiceV0 callServiceV0;

    /**
     * Aop Proxy Test.
     * 1. 프록시 호출 -> 2. 어드바이스 호출 -> 3. 실제 external 호출
     * callServiceV0 에는 external method 가 있는데, external method 를 호출한다.
     * java 에서는 메소드 앞에 별도 참조가 없으면 this 라는 뜻으로 자기 자신의 인스턴스를 가리킨다.,
     * 결과 적으로 자기 자신의 내부 메서드를 호출하는 this.internal() 이 되는데 , 여기서 this 는 실제 대상 객체 (target) 의
     * 인스턴스를 뜻한다.
     * 이러한 내부 호출은 프록시를 거치지 않는다. 따라서 어드바이스도 적용할 수 없다.
     */
    @Test
    void external() {
//        log.info("target={}", callServiceV0.getClass());
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}