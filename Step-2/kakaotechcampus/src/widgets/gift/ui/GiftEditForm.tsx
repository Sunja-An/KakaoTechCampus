"use client";

import { CancelButton, TextInput, UploadButton } from "@/components";
import React from "react";
import { RequestGiftDto } from "../types/gift";
import { useGift } from "../hooks/useGift";
import { MODIFY_GIFT } from "../api/gift.action";
import { useRouter } from "next/navigation";

type GiftEditFormType = {
  id: number;
  props: RequestGiftDto;
};

function GiftEditForm({ id, props }: GiftEditFormType) {
  const router = useRouter();
  const {
    gift,
    onChangeGiftId,
    onChangeGiftName,
    onChangeGiftPhotoUrl,
    onChangeGiftPrice,
  } = useGift(props);

  const onSubmit = async () => {
    const res = await MODIFY_GIFT(id, gift);
    if (res === true) {
      alert("성공하였습니다.");
      router.replace(`/gift/${id}`);
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

export { GiftEditForm };
