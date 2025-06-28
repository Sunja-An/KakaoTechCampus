"use client";

import React from "react";
import { CancelButton, TextInput, UploadButton } from "@/components";
import { POST_GIFT } from "@/widgets/gift/api/gift.action";

function GiftForm() {
  return (
    <form
      action=""
      method="post"
      className="box-border px-60 w-full flex flex-col justify-start items-start gap-8"
    >
      <div className="w-full flex flex-col justify-start items-start gap-4">
        <TextInput
          id="giftId"
          name="giftId"
          title="상품 ID"
          placeholder="상품 ID를 입력해주세요"
        />
        <TextInput
          id="giftName"
          name="giftName"
          title="상품 명"
          placeholder="상품 명을 입력해주세요"
        />
        <TextInput
          id="giftPrice"
          name="giftPrice"
          title="상품 가격"
          placeholder="상품 가격을 입력해주세요"
        />
        <TextInput
          id="giftPhotoUrl"
          name="giftPhotoUrl"
          title="상품 사진 URL"
          placeholder="상품 사진 URL을 입력해주세요"
        />
      </div>
      <div className="w-full flex justify-center items-center gap-8">
        <CancelButton />
        <UploadButton />
      </div>
    </form>
  );
}

export { GiftForm };
