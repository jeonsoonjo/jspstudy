package batch;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.BoardDAO;
import dto.BoardDTO;

public class TopHitJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		List<BoardDTO> topHit = BoardDAO.getInstance().topHit();
		
		try {
			System.out.println(topHit);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

