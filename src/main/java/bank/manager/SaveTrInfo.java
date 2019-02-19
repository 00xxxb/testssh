package bank.manager;

import bank.model.RecordBean;
import org.aspectj.lang.JoinPoint;

public interface SaveTrInfo {
    void saveRecord(JoinPoint jp, int id);
}
