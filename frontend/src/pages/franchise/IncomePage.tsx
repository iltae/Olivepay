import { useState, useEffect } from 'react';
import { useAtom } from 'jotai';
// import { useQuery } from '@tanstack/react-query';
// import { getFranchiseIncome } from '../../api/transactionApi';
import {
  Layout,
  BackButton,
  PageTitle,
  // Loader,
  Card,
  Button,
} from '../../component/common';
import { franchiseIncomeAtom } from '../../atoms/franchiseAtom';
import { groupByDate } from '../../utils/dateUtils';

const IncomePage = () => {
  const [income] = useAtom(franchiseIncomeAtom);
  const [index, setIndex] = useState(1);
  const [hasMore, setHasMore] = useState(true);

  useEffect(() => {
    console.log(index);
    setHasMore(false);
  }, []);

  // const franchiseId = 1;

  //   const { data, isLoading, error, isSuccess } = useQuery({
  //     queryKey: ['transaction', franchiseId, index],
  //     queryFn: () => getFranchiseIncome(franchiseId, index),
  //   });

  //   if (isSuccess && data) {
  //     if (data.length < 20) {
  //       setHasMore(false);
  //     } else {
  //       setIncome((prevIncome) => [...prevIncome, ...data]);
  //     }
  //   }

  //   if (isLoading) return <Loader />;

  //   if (error) return <div>쿠폰 로딩 실패</div>;

  const groupedIncome = groupByDate(income);
  return (
    <Layout className="px-8">
      <header className="mt-4 flex items-center justify-between">
        <BackButton />
        <PageTitle title="가맹점 결제 내역" />
        <div className="w-8" />
      </header>
      <main className="mt-4 flex flex-col gap-4">
        {Object.keys(groupedIncome).map((date) => (
          <div key={date}>
            <h2 className="text-md my-4 font-bold text-DARKBASE">{date}</h2>
            <div className="flex flex-col gap-4">
              {groupedIncome[date].map((el) => (
                <Card
                  key={el.transactionId}
                  variant="payment"
                  title="+"
                  spend={el.amount}
                  details={el.details}
                />
              ))}
            </div>
          </div>
        ))}
        <div className="mt-4 text-center">
          {hasMore && (
            <Button
              label="더보기"
              variant="secondary"
              onClick={() => setIndex((prev) => prev + 1)}
            />
          )}
        </div>
      </main>
    </Layout>
  );
};

export default IncomePage;