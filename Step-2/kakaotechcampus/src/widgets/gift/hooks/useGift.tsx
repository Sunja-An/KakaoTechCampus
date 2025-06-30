"use client";

import { ChangeEvent, useState } from "react";
import { type RequestGiftDto } from "@/widgets/gift/types/gift";

function useGift(props?: RequestGiftDto) {
  const [gift, setGift] = useState<RequestGiftDto>({
    giftId: props?.giftId ?? 0,
    giftName: props?.giftName ?? "",
    giftPrice: props?.giftPrice ?? 0,
    giftPhotoUrl: props?.giftPhotoUrl ?? "",
  });

  const onChangeGiftId = (e: ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    const parsedValue = Number(value);
    if (isNaN(parsedValue)) {
      return;
    }
    setGift({
      ...gift,
      giftId: parsedValue,
    });
  };

  const onChangeGiftName = (e: ChangeEvent<HTMLInputElement>) => {
    setGift({
      ...gift,
      giftName: e.target.value,
    });
  };

  const onChangeGiftPrice = (e: ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    const parsedValue = Number(value);
    if (isNaN(parsedValue)) {
      return;
    }
    setGift({
      ...gift,
      giftPrice: parsedValue,
    });
  };

  const onChangeGiftPhotoUrl = (e: ChangeEvent<HTMLInputElement>) => {
    setGift({
      ...gift,
      giftPhotoUrl: e.target.value,
    });
  };

  return {
    gift,
    onChangeGiftId,
    onChangeGiftName,
    onChangeGiftPrice,
    onChangeGiftPhotoUrl,
  };
}

export { useGift };
