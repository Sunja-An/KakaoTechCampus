"use server";

import { KTC_API } from "@/config";
import { type RequestGiftDto } from "@/widgets/gift/types/gift";

export const POST_GIFT = async (props: RequestGiftDto) => {
  try {
    const res = await KTC_API.post("/", props);
    if (res.status === 201) {
      return res.data;
    }
  } catch (err) {
    return err;
  }
};

export const DELETE_GIFT = async (id: number) => {
  try {
    const res = await KTC_API.delete(`/gift/${id}`);
    if (res.status === 204) {
      return res.data;
    }
  } catch (err) {
    return err;
  }
};

export const GET_GIFT = async (id: number) => {
  try {
    const res = await KTC_API.get(`/gift/${id}`);
    if (res.status === 200) {
      return res.data;
    }
  } catch (err) {
    return err;
  }
};

export const GET_GIFTS = async () => {
  try {
    const res = await KTC_API.get("/gift");
    if (res.status === 200) {
      return res.data;
    }
  } catch (err) {
    return err;
  }
};
