"use client";

import React from "react";
import { CancelButton, TextInput, UploadButton } from "@/components";
import { POST_GIFT } from "@/widgets/gift/api/gift.action";
import { useGift } from "../hooks/useGift";
import { useRouter } from "next/navigation";

function GiftForm() {
  const router = useRouter();
  const {
    gift,
    onChangeGiftId,
    onChangeGiftName,
    onChangeGiftPhotoUrl,
    onChangeGiftPrice,
  } = useGift();

  const onSubmit = async () => {
    const res = await POST_GIFT(gift);
    if (res === true) {
      alert("성공하였습니다.");
      router.replace("/gift");
    } else {
      alert("실패하였습니다.");
    }
  };
  return (
    <div className="box-border px-60 w-full flex flex-col justify-start items-start gap-8">
      <div className="w-full flex flex-col justify-start items-start gap-4">
        <TextInput
          id="giftId"
          name="giftId"
          title="상품 ID"
          value={gift.giftId}
          placeholder="상품 ID를 입력해주세요"
          onChange={onChangeGiftId}
        />
        <TextInput
          id="giftName"
          name="giftName"
          title="상품 명"
          value={gift.giftName}
          placeholder="상품 명을 입력해주세요"
          onChange={onChangeGiftName}
        />
        <TextInput
          id="giftPrice"
          name="giftPrice"
          title="상품 가격"
          value={gift.giftPrice}
          placeholder="상품 가격을 입력해주세요"
          onChange={onChangeGiftPrice}
        />
        <TextInput
          id="giftPhotoUrl"
          name="giftPhotoUrl"
          title="상품 사진 URL"
          value={gift.giftPhotoUrl}
          placeholder="상품 사진 URL을 입력해주세요"
          onChange={onChangeGiftPhotoUrl}
        />
      </div>
      <div className="w-full flex justify-center items-center gap-8">
        <CancelButton />
        <UploadButton onClick={onSubmit} />
      </div>
    </div>
  );
}

export { GiftForm };
