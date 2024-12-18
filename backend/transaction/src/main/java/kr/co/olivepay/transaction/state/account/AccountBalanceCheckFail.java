package kr.co.olivepay.transaction.state.account;

import kr.co.olivepay.core.transaction.topic.Topic;
import kr.co.olivepay.core.transaction.topic.event.payment.result.PaymentFailEvent;
import kr.co.olivepay.transaction.PaymentSaga;
import kr.co.olivepay.transaction.mapper.PaymentSagaMapper;
import kr.co.olivepay.transaction.state.PaymentState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class AccountBalanceCheckFail implements PaymentState {

    /**
     * 결제 프로세스 실패
     *
     * @param paymentSaga
     */
    @Override
    public void operate(PaymentSaga paymentSaga) {
        publishPaymentFailEvent(paymentSaga);
    }

    /**
     * 결제 프로세스 실패 이벤트 발행
     *
     * @param paymentSaga
     */
    private void publishPaymentFailEvent(PaymentSaga paymentSaga) {
        PaymentFailEvent paymentFailEvent
                = PaymentSagaMapper.toPaymentFailEvent(paymentSaga);
        //payment 서비스로 결제 프로세스 실패 이벤트 발행
        log.info("잔액 체크 실패 -> 결제 실패 이벤트 발행 : [{}]", paymentFailEvent);
        paymentSaga.publishEvent(
                Topic.PAYMENT_FAIL,
                paymentSaga.getKey(),
                paymentFailEvent
        );
    }
}
