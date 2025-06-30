"use server";

import { KTC_API } from "@/config";
import type {
  RequestModifyGiftDto,
  RequestGiftDto,
} from "@/widgets/gift/types/gift";

export const POST_GIFT = async (props: RequestGiftDto) => {
  try {
    const res = await KTC_API.post("/api/products", props);
    if (res.status === 201) {
      return true;
    }
  } catch (err) {
    return err;
  }
};

export const MODIFY_GIFT = async (props: RequestModifyGiftDto) => {
  try {
    const res = await KTC_API.patch(`/api/products`, props);
    if (res.status === 201) {
      return true;
    }
  } catch (err) {
    return err;
  }
};

export const DELETE_GIFT = async (id: number) => {
  try {
    const res = await KTC_API.delete(`/api/products/${id}`);
    if (res.status === 204) {
      return res.data;
    }
  } catch (err) {
    return err;
  }
};

export const GET_GIFT = async (id: number) => {
  try {
    const res = await KTC_API.get(`/api/products/${id}`);
    if (res.status === 200) {
      return res.data;
    }
  } catch (err) {
    return err;
  }
};

export const GET_GIFTS = async () => {
  try {
    const res = await KTC_API.get("/api/products");
    if (res.status === 200) {
      return res.data;
    }
  } catch (err) {
    return err;
  }
};
