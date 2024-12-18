import clsx from 'clsx';
import { getFranchiseCategoryEmoji } from '../../utils/category';

import { FranchiseProps } from '../../types/franchise';
import { franchiseCategory } from '../../types/franchise';

const FranchiseInfo: React.FC<FranchiseProps> = ({
  franchiseName,
  category,
  className,
}) => {
  return (
    <div className={clsx('flex flex-col gap-2', className)}>
      <p>
        반갑습니다
        {getFranchiseCategoryEmoji(
          franchiseCategory[
            category as unknown as keyof typeof franchiseCategory
          ],
        )}
      </p>
      <p>
        <span className="text-2xl font-semibold">{franchiseName}</span> 사장님
      </p>
    </div>
  );
};

export default FranchiseInfo;
