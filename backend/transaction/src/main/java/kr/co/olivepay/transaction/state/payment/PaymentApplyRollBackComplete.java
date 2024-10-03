package kr.co.olivepay.transaction.state.payment;

import kr.co.olivepay.core.transaction.topic.Topic;
import kr.co.olivepay.core.transaction.topic.event.payment.result.PaymentFailEvent;
import kr.co.olivepay.transaction.PaymentSaga;
import kr.co.olivepay.transaction.mapper.PaymentSagaMapper;
import kr.co.olivepay.transaction.state.PaymentState;

public class PaymentApplyRollBackComplete implements PaymentState {


    /**
     * 롤백 적용 완료 -> 결제 실패 (종료)
     * @param paymentSaga
     */
    @Override
    public void operate(PaymentSaga paymentSaga) {
        publishPaymentFailEvent(paymentSaga);
    }

    private static void publishPaymentFailEvent(PaymentSaga paymentSaga) {
        PaymentFailEvent paymentFailEvent
                = PaymentSagaMapper.toPaymentFailEvent(paymentSaga);
        paymentSaga.publishEvent(
                Topic.PAYMENT_FAIL,
                paymentSaga.getKey(),
                paymentFailEvent
        );
    }
}
