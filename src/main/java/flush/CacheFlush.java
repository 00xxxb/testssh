package flush;

import bank.manager.Manager;
import bank.manager.ManagerImpl;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class CacheFlush implements Runnable {
    private HttpSession session;
    private int time;
    private Manager manager;

    /*
    * 根据用户使用频率分级，设定缓存刷新时间
    * */
    public void setTime(char level) {
        switch (level){
            case 'A':
                time = 1;
                break;
            case 'B':
                time = 2;
                break;
            case 'C':
                time = 5;
                break;
        }
    }

    public CacheFlush(HttpSession session,Manager manager){
        this.session = session;
        this.manager = manager;
    }

    @Override
    public void run() {
        loop:while ((boolean)session.getAttribute("online")) {
            try {
                Thread.sleep(1000 * 20*time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (session.getAttribute("online")==null||!(boolean) session.getAttribute("online")) {
                    System.out.println("offline");
                    break loop;
                }
            }catch (IllegalStateException e){
                System.out.println("offline");
                break loop;
            }
            Map map = (Map) session.getAttribute("pageInfo");
            int maxPages;
            int id = (int) session.getAttribute("user");
            map.put("maxPages", manager.getMaxPages(id));
            maxPages = (int)map.get("maxPages");
            /*
            * 判断交易记录是否翻页，而缓存未及时更新
            * */
            int page = maxPages-1;
            while (map.get(page)!=null&&((List)map.get(page)).size()!=8){
                map.put(page,manager.getInfo(id,page));
                page--;
            }
            map.put(maxPages,manager.getInfo(id,maxPages));
            session.setAttribute("pageInfo",map);
        }
    }
}
