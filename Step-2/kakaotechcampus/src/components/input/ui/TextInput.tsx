import React, { type ChangeEvent } from "react";

type TextInputType = {
  name: string;
  id: string;
  title: string;
  value: string | number;
  placeholder?: string;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
};

function TextInput({
  id,
  name,
  title,
  value,
  placeholder,
  onChange,
}: TextInputType) {
  return (
    <div className="w-full flex flex-col justify-start items-start gap-4">
      <label htmlFor={name}>{title}</label>
      <input
        type="text"
        name={name}
        id={id}
        value={value}
        placeholder={placeholder ?? "값을 입력해주세요"}
        onChange={onChange}
        className="box-border p-4 px-3 w-full h-12 focus:outline-none text-sm text-black font-medium border border-black rounded-md"
      />
    </div>
  );
}

export { TextInput };
