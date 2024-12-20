export type UniMobPushCallback = {
	onCallback : (res : any) => void
}

export type UniMobPushReceiver = {
	/**
	 * 自定义消息回调
	 */
	onCustomMessageReceive : (message : string) => void,

	/**
	 * 通知回调
	 */
	onNotifyMessageReceive : (message : string) => void,

	/**
	 * 通知点击回调（当设置了自定义的通知栏显示样式后，将不会收到此事件）
	 */
	onNotifyMessageOpenedReceive : (message : string) => void,

	/**
	 * 获取、设置、删除和清空tags回调
	 *
	 * @param tags      操作的tags
	 * @param operation 操作说明（0 获取， 1 设置， 2 删除，3 清空）
	 * @param errorCode 操作结果（0 成功，其他失败）
	 */
	onTagsCallback : (tags : Array<string>, operation : number, errorCode : number) => void,

	/**
	 * 获取、设置、删除别名回调
	 *
	 * @param alias     操作的别名
	 * @param operation 操作说明（0 获取， 1 设置， 2 删除）
	 * @param errorCode 操作结果（0 成功，其他失败）
	 */
	onAliasCallback : (alias : string, operation : number, errorCode : number) => void,
}


/* 获取RegistrationId */
export type RegistrationId = (callback : UniMobPushCallback) => void

/* 推送监听接口 */
export type AddPushReceiver = (receiver : UniMobPushReceiver) => void

/* 关闭推送功能 */
export type StopPush = () => void

/* 重启推送功能 */
export type RestartPush = () => void

/*  获取推送是否关闭 */
export type IsPushStopped = (callback : UniMobPushCallback) => void

/* 设置别名 */
export type SetAlias = (alias : string) => void

/* 获取别名 */
export type GetAlias = () => void

/* 设置别名 */
export type DeleteAlias = () => void

/* 添加标签 */
export type AddTags = (tags : string[]) => void

/* 获取标签 */
export type GetTags = () => void

/* 删除标签 */
export type DeleteTags = (tags : string[]) => void

/* 清除标签 */
export type CleanTags = () => void

/* 设置角标状态 */
export type SetShowBadge = (showBadge : boolean) => void

/* 获取角标状态 */
export type GetShowBadge = (callback : UniMobPushCallback) => void

/* 设置静默时间 */
export type SetSilenceTime = (startHour : Int, startMinute : Int, endHour : Int, endMinute : Int) => void