import React, { useState } from "react";
import { type RequestGiftDto } from "@/widgets/gift/types/gift";

function useGift() {
  const [gift, setGift] = useState<RequestGiftDto>({
    giftId: 0,
    giftName: "",
    giftPrice: 0,
    giftPhotoUrl: "",
  });
  return gift;
}

export { useGift };
