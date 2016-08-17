package com.ofweek.live.core.modules.sys.utils;

import java.util.HashMap;
import java.util.Map;

import com.ofweek.live.core.common.utils.SpringContextHolder;
import com.ofweek.live.core.modules.sys.dao.SequenceDao;
import com.ofweek.live.core.modules.sys.entity.Sequence;

/**
 * sequence工具类
 * 
 * @author tangqian
 * 
 */
public class SequenceUtils {

	private static SequenceDao sequenceDao = SpringContextHolder.getBean(SequenceDao.class);

	private static final Map<String, KeyInfo> keyMap = new HashMap<String, KeyInfo>(32);

	private static final int POOL_SIZE = 20;

	/**
	 * 获取下一个sequence值
	 * 
	 * @param keyName
	 * @return
	 */
	public static synchronized String getNextString(String keyName) {
		return String.valueOf(getNextInt(keyName));
	}

	/**
	 * 获取下一个sequence值
	 * 
	 * @param keyName
	 *            Sequence名称
	 * @return 下一个Sequence键值
	 */
	private static synchronized int getNextInt(String keyName) {
		KeyInfo keyInfo = null;
		if (keyMap.containsKey(keyName)) {
			keyInfo = keyMap.get(keyName);
		} else {
			keyInfo = new KeyInfo(keyName, POOL_SIZE);
			keyMap.put(keyName, keyInfo);
		}
		return keyInfo.getNextValue();
	}

	private static class KeyInfo {

		private int maxValue;

		private int nextValue;

		/**
		 * Sequence缓存多少个值
		 */
		private int poolSize;

		/**
		 * Sequence的名称
		 */
		private String keyName;

		public KeyInfo(String keyName, int poolSize) {
			this.poolSize = poolSize;
			this.keyName = keyName;
			retrieveFromDB();
		}

		/**
		 * 获取下一个Sequence值
		 * 
		 * @return 下一个Sequence值
		 * 
		 */
		public synchronized int getNextValue() {
			if (nextValue >= maxValue) {
				retrieveFromDB();
			}
			return nextValue++;
		}

		/**
		 * 执行Sequence表初始化和更新工作
		 * 
		 */
		private void retrieveFromDB() {
			Sequence seq = sequenceDao.get(keyName);
			if (seq != null) {
				nextValue = seq.getNextId();
				maxValue = nextValue + poolSize;

				seq.setStep(poolSize);
				seq.preUpdate();
				sequenceDao.update(seq);
			} else {
				nextValue = 1;
				maxValue = nextValue + poolSize;

				seq = new Sequence();
				seq.setName(keyName);
				seq.setNextId(maxValue);
				seq.preInsert();
				sequenceDao.insert(seq);
			}
		}
	}

}
