package kr.co.olivepay.franchise.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.olivepay.franchise.dto.req.ReviewCreateReq;
import kr.co.olivepay.franchise.dto.res.EmptyReviewRes;
import kr.co.olivepay.franchise.dto.res.FranchiseBasicRes;
import kr.co.olivepay.franchise.dto.res.FranchiseMinimalRes;
import kr.co.olivepay.franchise.dto.res.FranchiseReviewRes;
import kr.co.olivepay.franchise.dto.res.LikedFranchiseRes;
import kr.co.olivepay.franchise.dto.res.UserReviewRes;
import kr.co.olivepay.franchise.entity.Category;
import kr.co.olivepay.franchise.global.enums.NoneResponse;
import kr.co.olivepay.franchise.global.enums.SuccessCode;
import kr.co.olivepay.franchise.global.response.Response;
import kr.co.olivepay.franchise.global.response.SuccessResponse;
import kr.co.olivepay.franchise.service.ReviewService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/franchises/reviews")
@RequiredArgsConstructor
public class ReviewController {

	//private final ReviewService reviewService;

	@PostMapping
	@Operation(description = """
		리뷰를 등록합니다.
		작성자 id, 가맹점 id, 내용, 평점을 필요로 합니다.
		""", summary = "리뷰 등록")
	public ResponseEntity<Response<NoneResponse>> registerReview(
		@RequestBody ReviewCreateReq request) {
		// Long memberId = 1L; //TODO: Auth 처리
		// SuccessResponse<NoneResponse> response = reviewService.registerReview(memberId, request);

		SuccessResponse<NoneResponse> response = new SuccessResponse<>(SuccessCode.REVIEW_REGISTER_SUCCESS,
			NoneResponse.NONE);

		return Response.success(response);
	}

	@DeleteMapping("/{reviewId}")
	@Operation(description = """
		리뷰 id에 해당하는 리뷰를 삭제합니다.
		""", summary = "리뷰 삭제")
	public ResponseEntity<Response<NoneResponse>> deleteReview(
		@PathVariable Long reviewId
	) {
		// Long memberId = 1L; //TODO: Auth 처리
		// SuccessResponse<NoneResponse> response = reviewService.removeReview(reviewId);

		SuccessResponse<NoneResponse> response = new SuccessResponse<>(SuccessCode.REVIEW_DELETE_SUCCESS,
			NoneResponse.NONE);

		return Response.success(response);
	}

	@GetMapping("/user")
	@Operation(description = """
		내가 작성한 모든 리뷰를 조회합니다.
		20개 단위로 페이징 처리가 이뤄집니다.
		""", summary = "내가 작성한 리뷰 조회")
	public ResponseEntity<Response<List<FranchiseReviewRes>>> getMyReviewList(
	) {
		// Long memberId = 1L; //TODO: Auth 처리
		// //TODO: 페이징 처리
		// SuccessResponse<List<FranchiseReviewRes>> response = reviewService.getMyReviewList(memberId);

		FranchiseMinimalRes franchise1 = FranchiseMinimalRes.builder()
															.id(1L)
															.name("멀티 캠퍼스")
															.build();
		FranchiseMinimalRes franchise2 = FranchiseMinimalRes.builder()
															.id(2L)
															.name("아웃백 스테이크하우스")
															.build();

		FranchiseReviewRes dto1 = FranchiseReviewRes.builder()
													.reviewId(11111L)
													.franchise(franchise1)
													.content("맛있게 먹자~")
													.stars(3)
													.build();

		FranchiseReviewRes dto2 = FranchiseReviewRes.builder()
													.reviewId(22222L)
													.franchise(franchise2)
													.content("ㄱㅊㄱㅊ")
													.stars(5)
													.build();

		List<FranchiseReviewRes> dtoList = new ArrayList<>();
		dtoList.add(dto1);
		dtoList.add(dto2);

		SuccessResponse<List<FranchiseReviewRes>> response = new SuccessResponse<>(
			SuccessCode.USER_REVIEW_SEARCH_SUCCESS, dtoList);

		return Response.success(response);
	}

	@GetMapping("/{franchiseId}")
	@Operation(description = """
		특정 가맹점에 대한 모든 리뷰를 조회합니다.
		20개 단위로 페이징 처리가 이뤄집니다.
		""", summary = "가맹점 리뷰 조회")
	public ResponseEntity<Response<List<UserReviewRes>>> getFranchiseReviewList(
		@PathVariable Long franchiseId
	) {
		// //TODO: 페이징 처리
		// SuccessResponse<List<UserReviewRes>> response = reviewService.getFranchiseReviewList(franchiseId);

		UserReviewRes dto1 = UserReviewRes.builder()
										  .reviewId(13234L)
										  .memberId(1L)
										  .memberName("말벌아저씨")
										  .content("맛있게 먹자!")
										  .stars(3)
										  .build();
		UserReviewRes dto2 = UserReviewRes.builder()
										  .reviewId(13234L)
										  .memberId(5L)
										  .memberName("왕승페이")
										  .content("꽤 괜찮")
										  .stars(5)
										  .build();

		List<UserReviewRes> dtoList = new ArrayList<>();
		dtoList.add(dto1);
		dtoList.add(dto2);
		SuccessResponse<List<UserReviewRes>> response = new SuccessResponse<>(
			SuccessCode.LIKED_FRANCHISE_SEARCH_SUCCESS, dtoList);
		return Response.success(response);
	}

	@GetMapping("/available")
	@Operation(description = """
		사용자가 결제 내용은 있지만 작성하지 않은 모든 리뷰를 조회합니다.
		20개 단위로 페이징 처리가 이뤄집니다.
		""", summary = "미작성 리뷰 조회")
	public ResponseEntity<Response<List<EmptyReviewRes>>> getAvailableReviewList(
	) {
		// Long memberId = 1L; //TODO: Auth 처리
		// SuccessResponse<List<EmptyReviewRes>> response = reviewService.getAvailableReviewList(memberId);

		FranchiseMinimalRes franchise1 = FranchiseMinimalRes.builder()
															.id(1L)
															.name("멀티 캠퍼스")
															.build();
		FranchiseMinimalRes franchise2 = FranchiseMinimalRes.builder()
															.id(2L)
															.name("아웃백 스테이크하우스")
															.build();

		EmptyReviewRes dto1 = EmptyReviewRes.builder()
											.reviewId(11111L)
											.franchise(franchise1)
											.createdAt(LocalDateTime.parse("2024-09-20T14:09:12"))
											.build();
		EmptyReviewRes dto2 = EmptyReviewRes.builder()
											.reviewId(22222L)
											.franchise(franchise2)
											.createdAt(LocalDateTime.parse("2024-08-11T12:32:54"))
											.build();

		List<EmptyReviewRes> dtoList = new ArrayList<>();
		dtoList.add(dto1);
		dtoList.add(dto2);
		SuccessResponse<List<EmptyReviewRes>> response = new SuccessResponse<>(
			SuccessCode.AVAILABLE_REVIEW_SEARCH_SUCCESS, dtoList);

		return Response.success(response);
	}

}