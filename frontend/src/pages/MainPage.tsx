import { useEffect } from 'react';
import { useAtom } from 'jotai';
import { useQuery } from '@tanstack/react-query';
import { userAtom } from '../atoms';

import { getUsersInfo } from '../api/userApi';
import {
  Layout,
  Button,
  BackButton,
  Input,
  Loader,
  Card,
  Success,
  BottomUp,
} from '../component/common';
import Stepper from '../component/common/Stepper';

const MainPage = () => {
  const [users, setUsers] = useAtom(userAtom);
  const { data, error, isLoading } = useQuery({
    queryKey: ['users'],
    queryFn: getUsersInfo,
  });

  useEffect(() => {
    if (data) {
      setUsers(data);
    }
  }, [data, setUsers]);

  if (isLoading) return <Loader />;

  if (error) return <div>에러 발생: {error.message}</div>;

  return (
    <>
      <Layout>
        <Card
          variant="restaurant"
          title="멀티캠퍼스"
          category="한식"
          score={4.2}
          like={32}
        />
        <Card
          variant="payment"
          title="멀티캠퍼스"
          spend={12000}
          details={[
            {
              type: 'card',
              name: '꿈나무카드',
              amount: 9000,
            },
            {
              type: 'coupon',
              name: '쿠폰',
              amount: 4000,
            },
            {
              type: 'card',
              name: '신한4582',
              amount: 500,
            },
          ]}
        />
        <Card
          variant="donation"
          title="대우부대찌개"
          price={50000}
          date="24.07.20"
          location="서울시 강남구"
        />
        <hr className="mt-10" />
        <Card
          variant="review"
          title="브라운박사"
          score={4}
          content="우리 동네에 이런 맛집이 있다니! 우리 동네에 이런 맛집이 있다니! 우리 동네에 이런 맛집이 있다니! 우리 동네에 이런 맛집이 있다니!"
        />
        <Card
          variant="review"
          title="브라운박사"
          score={4}
          content="우리 동네에 이런 맛집이 있다니!"
        />
      </Layout>
      <BottomUp children={<div>짜잔~~!</div>} />
    </>
  );
};

export default MainPage;
