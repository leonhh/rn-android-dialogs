interface BaseDialogOptions {
  title: string;
  negativeButtonText?: string;
}
export interface AlertDialogOptions extends BaseDialogOptions {
  message?: string;
  positiveButtonText: string;
}
export interface SelectionDialogOptions extends BaseDialogOptions {
  options: string[];
}
export interface RadioButtonDialogOptions extends BaseDialogOptions {
  options: string[];
  selectedIndex?: number;
  positiveButtonText?: string;
}

// Return types for each dialog function
export type AlertDialogResult = boolean; // true for positive, false for negative
export type SelectionDialogResult = number; // selected index or -1 for cancel
export type RadioButtonDialogResult = number; // selected index or -1 for cancel
